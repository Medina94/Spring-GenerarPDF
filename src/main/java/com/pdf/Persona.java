package com.pdf;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface Persona {
	public List<PersonaImpl> permutarNombre(PersonaImpl per);
	public List<PersonaImpl> listarPermutaciones(PersonaImpl per);
	public byte[] convert() throws IOException;
}
