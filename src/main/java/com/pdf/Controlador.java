package com.pdf;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

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
	@Autowired
	private PersonaRepo repo;
	
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	/*@RequestMapping("/permutar")
	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<PersonaImpl> traerPersonas(@RequestBody PersonaImpl per){
		return servicio.traerLista(per);
	}
	*/
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	@RequestMapping("/permutar")
	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<PersonaImpl> traerPersonas(@RequestBody PersonaImpl per){
		return servicio.getListado(per);
	}
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	// agregando al path con requestMapping no funciona
	//@RequestMapping("/download")
    @GetMapping(path = "/download", produces = {MediaType.APPLICATION_PDF_VALUE}) //tambien funciona usando: produces = ("application/pdf")
	public byte[] descargar(){
		
		try {
			return persona.convert();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new byte[] {};
	}
	//-----------------------------------------------------------------------------------------------------------------------------------------------	
	@RequestMapping("/mail")
	
	@GetMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public void sendMail(@RequestBody Mail mail) {
		//String mensaje = body + "\n\nDatos contacto: "+"\nNombre: "+name+"\nEmail: "+mail;
		send.enviarMail(mail);
	}
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	@PostMapping(path = "/insertar")
	public PersonaBD insertarPersona(@RequestBody PersonaImpl per) {
		PersonaBD p = new PersonaBD();
		p.setDni(Long.parseLong(per.getId()));
		p.setNombre(per.getNombre());
		return repo.save(p);
	}
	//------------------------------------------------------------------------------------------------------------------------------------------------
	@PostMapping(path = "/insertar_muchos")
	public List<PersonaBD> insertarMuchos(@RequestBody PersonaImpl per){
		List<PersonaImpl> lista = servicio.traerLista(per);
		List<PersonaBD> listaBD = new ArrayList();
		for(int i=0; i<lista.size(); i++) {
			PersonaBD p = new PersonaBD();
			p.setDni(Long.parseLong(lista.get(i).getId()));
			p.setNombre(lista.get(i).getNombre());
			listaBD.add(p);
		}
		return repo.saveAll(listaBD);
	}
}
