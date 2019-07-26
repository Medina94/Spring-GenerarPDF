package com.pdf;

import java.util.List;

public interface Servicio {
	public List<PersonaImpl> traerLista(PersonaImpl per);
	
	public List<PersonaImpl> getListado(PersonaImpl per);
	
}
