package com.pdf;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "Tabla_Personas")
public class PersonaBD {
	@Id
	@GeneratedValue()
	private int id;
	@Column(name = "DNI")
	private long dni;
	@Column(name = "Nombre", length = 30)
	private  String nombre;
	//---------------------------------------------------------------------------------------------------------------------------------------------------------------------
	public long getDni() {
		return dni;
	}
	public void setDni(long dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
