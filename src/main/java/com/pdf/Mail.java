package com.pdf;

public class Mail {
	private String emisor;
	private String receptor;
	private String asunto;
	private String mensaje;
	//-------------------------------------------------------------
	public Mail(String emisor, String receptor, String asunto, String mensaje) {
		this.emisor = emisor;
		this.receptor = receptor;
		this.asunto = asunto;
		this.mensaje = mensaje;
	}
	public Mail() {};
	//-------------------------------------------------------------
	public String getEmisor() {
		return emisor;
	}
	public void setEmisor(String emisor) {
		this.emisor = emisor;
	}
	public String getReceptor() {
		return receptor;
	}
	public void setReceptor(String receptor) {
		this.receptor = receptor;
	}
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	//-----------------------------------------------------------------------
	
}
