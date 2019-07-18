package com.pdf;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class PersonaImpl implements Persona{
	private String id;
	private String nombre;	
	//---------------------------------------------------------------------------------
	//CONSTRUCTOR
	public PersonaImpl() {}
	
	public PersonaImpl(String id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}

	//---------------------------------------------------------------------------------
	//get y set
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	//---------------------------------------------------------------------------------

	@Override
	public List<PersonaImpl> permutarNombre(PersonaImpl per) {
		List<PersonaImpl> lista = new ArrayList();
		List<String> listaString = new ArrayList();
		String txt = per.getNombre();
		char[] array = txt.toCharArray();
		char[] copia = array;
		char aux;
		listaString.add(String.valueOf(array));
		for (int i = 0; i < txt.length(); i++) {
			for (int j = 0; j < txt.length(); j++) {
				for (int k = 0; k < txt.length(); k++) {
					for (int l = 0; l < txt.length(); l++) {
						aux = copia[k];
						copia[k] = copia[l];
						copia[l] = aux;
						if (!listaString.contains(String.valueOf(copia))) {
							listaString.add(String.valueOf(copia));
						}
					}
					aux = copia[j];
					copia[j] = copia[k];
					copia[k] = aux;
				}
				aux = copia[i];
				copia[i] = copia[j];
				copia[j] = aux;
			}
		}

		for (int i = 0; i < listaString.size(); i++) {
			PersonaImpl p = new PersonaImpl();
			p.setId(""+(Integer.parseInt(per.id)+i));
			p.setNombre(listaString.get(i));

			lista.add(p);

		}
		CreatePDF crear = new CreatePDF();
		crear.cargarLista(lista);
		crear.traerReporte();
		crear.crearReporte();
		crear.GuardarPDFenRuta();
		return lista;
	}
}
