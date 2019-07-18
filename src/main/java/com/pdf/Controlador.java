package com.pdf;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lista")
public class Controlador {
	@Autowired
	private Servicio servicio;
	@Autowired
	private Persona persona;
	
	//------------------------------------------------------------------------------------
	/*@RequestMapping("/permutar")
	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<PersonaImpl> traerPersonas(@RequestBody PersonaImpl per){
		return servicio.traerLista(per);
	}
	*/
	
	@RequestMapping("/permutar")
	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<PersonaImpl> traerPersonas(@RequestBody PersonaImpl per){
		return servicio.traerLista(per);
	}
}
