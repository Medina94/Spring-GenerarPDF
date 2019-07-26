package com.pdf;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.stereotype.Repository;

@Repository
@Entity
public class PersonaImpl implements Persona {
	@Id ()
	@GeneratedValue
	private int id;
	/*@Column (name = "DNI")
	private long dni;*/
	@Column(name = "Nombre", length = 30)
	private String nombre;

	// private String email;
	// ---------------------------------------------------------------------------------
	// CONSTRUCTOR
	public PersonaImpl() {
	}

	public PersonaImpl(int id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}

	public PersonaImpl(int id, String nombre, String email) {
		super();
		this.id = id;
		this.nombre = nombre;
		// this.email = email;
	}

	// ---------------------------------------------------------------------------------
	// get y set
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/*
	 * public String getEmail() { return email; } public void setEmail(String email)
	 * { this.email = email; }
	 */
	// ---------------------------------------------------------------------------------

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
			p.setId(per.getId() + i);
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

	// -----------------------------------------------------------------------------------------------------------------
	// PERMUTACION DE STRING CON SET Y RECURSIVIDAD
	private Set<String> permutation(String prefix, String str) {
		Set<String> permutations = new HashSet<>();
		int n = str.length();
		if (n == 0) {
			permutations.add(prefix);
		} else {
			for (int i = 0; i < n; i++) {
				permutations.addAll(permutation(prefix + str.charAt(i), str.substring(i + 1, n) + str.substring(0, i)));
			}
		}
		return permutations;
	}

	// ------------------------------------------------------------------------------------------------------------------
	@Override
	public List<PersonaImpl> listarPermutaciones(PersonaImpl persona) {
		List<PersonaImpl> lista = new ArrayList();
		Set<String> permutaciones = permutation("", persona.getNombre());
		int index = 0;
		for (Iterator it = permutaciones.iterator(); it.hasNext();) {
			PersonaImpl per = new PersonaImpl();
			per.setId(index);
			per.setNombre((String) it.next());
			lista.add(per);
			index++;
		}

		CreatePDF crear = new CreatePDF();
		crear.cargarLista(lista);
		crear.traerReporte();
		crear.crearReporte();
		crear.GuardarPDFenRuta();

		return lista;
	}

	// -----------------------------------------------------------------
	@Override
	public byte[] convert() throws IOException {
		// byte[] b = null;
		File file = new File("C:\\Users\\cmedina\\Desktop\\ReportePersona2.pdf");
		byte[] bytes = Files.readAllBytes(file.toPath());

		return bytes;
	}
	//----------------------------------------------------------------------

	@Override
	public List<PersonaImpl> listaPersonas(PersonaImpl persona) {
		List<PersonaImpl> lista = new ArrayList();
		Set<String> permutaciones = permutation("", persona.getNombre());
		int index = 0;
		for (Iterator it = permutaciones.iterator(); it.hasNext();) {
			PersonaImpl per = new PersonaImpl();
			per.setId(index);
			per.setNombre((String) it.next());
			lista.add(per);
			index++;
		}
		return lista;
	}
}
