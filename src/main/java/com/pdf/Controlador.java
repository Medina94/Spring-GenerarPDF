package com.pdf;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	// agregando al path con requestMapping no funciona
	//@RequestMapping("/download")
    @GetMapping(path = "/download", produces = {MediaType.APPLICATION_PDF_VALUE})
	public byte[] descargar(){
		
		try {
			return persona.convert();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new byte[] {};
	}
	
	
	
	@RequestMapping("/mail")
	@GetMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public void sendMail(@RequestBody Mail mail) {
		//String mensaje = body + "\n\nDatos contacto: "+"\nNombre: "+name+"\nEmail: "+mail;
		send.enviarMail(mail);
	}
	
}
