package com.pdf;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioImpl implements Servicio{
	@Autowired
	private Persona persona;
	
	//--------------------------------------------------------------------------------
	//GET Y SET
	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	//----------------------------------------------------------------------------------
	@Override
	public List<PersonaImpl> traerLista(PersonaImpl per) {
		
		return persona.permutarNombre(per);
	}

	//-----------------------------------------------------------------------------------
	@Override
	public List<PersonaImpl> getListado(PersonaImpl per) {
		return persona.listarPermutaciones(per);
	}
	
}
