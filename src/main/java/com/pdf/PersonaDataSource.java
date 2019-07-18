package com.pdf;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class PersonaDataSource implements JRDataSource{
	private List<PersonaImpl> listaPersona = new ArrayList();
	private int indicePersonaActual = -1;
	
	
	public void agregarPersona(PersonaImpl persona) {
		listaPersona.add(persona);
	}
	
	@Override
	public boolean next() throws JRException {
		return ++indicePersonaActual < listaPersona.size();
	}

	@Override
	public Object getFieldValue(JRField jrField) throws JRException {
		Object objeto = null;
		if("id".equalsIgnoreCase(jrField.getName())){
			objeto = listaPersona.get(indicePersonaActual).getId();
		}else if("nombre".equalsIgnoreCase(jrField.getName())) {
			objeto = listaPersona.get(indicePersonaActual).getNombre();
		}else {
			System.out.println("Field no especificado");
		}
		return objeto;
	}

}
