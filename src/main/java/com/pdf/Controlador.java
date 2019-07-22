package com.pdf;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lista")
public class Controlador {
	@Autowired
	private Servicio servicio;
	@Autowired
	private Persona persona;
	@Autowired
	private MailServicio send;
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
		return servicio.getListado(per);
	}
	
	@GetMapping("/mail")
	public void sendMail(/*@RequestParam("name") String name, @RequestParam("mail") String mail, @RequestParam("asunto") String subject, @RequestParam("mensaje") String body*/) {
		//String mensaje = body + "\n\nDatos contacto: "+"\nNombre: "+name+"\nEmail: "+mail;
		send.enviarMail();
	}
	
}
