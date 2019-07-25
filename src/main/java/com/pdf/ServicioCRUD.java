package com.pdf;

public interface ServicioCRUD {
	public PersonaImpl createPersona(PersonaImpl per);
	public void updatePersona(PersonaImpl per);
	public void deletePersona(PersonaImpl per);
	public PersonaImpl readPersona(PersonaImpl per);
}
