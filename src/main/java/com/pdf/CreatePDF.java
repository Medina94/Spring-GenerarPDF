package com.pdf;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.mail.javamail.MimeMessagePreparator;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class CreatePDF {
	private FileInputStream input;
	private JasperReport report;
	private JasperDesign design;
	private JasperPrint print;
	private JasperViewer view;
	private PersonaDataSource data = new PersonaDataSource();
	
	public void cargarLista(List<PersonaImpl> lista) {
		for(PersonaImpl persona : lista) {
			data.agregarPersona(persona);
		}
	}
	//--------------------------------------------------------------------------------------------------------------------------------------
	public void traerReporte() {
		try {
			input = new FileInputStream("C:\\Users\\cmedina\\Desktop\\reporte\\Reporte.jrxml");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	//--------------------------------------------------------------------------------------------------------------------------------------
	public void crearReporte() {
		try {
			design = JRXmlLoader.load(input);
			report = JasperCompileManager.compileReport(design);
			print = JasperFillManager.fillReport(report, null, data);
			
			GuardarPDFenRuta();
		} catch (JRException e) {
			e.printStackTrace();
		}
		
	}
	//--------------------------------------------------------------------------------------------------------------------------------------
	public void GuardarPDFenRuta() {
		try {
			JasperExportManager.exportReportToPdfFile(print, "C:\\Users\\cmedina\\Desktop\\ReportePersona2.pdf");
			
		} catch (JRException e) {
			e.printStackTrace();
		}
	}
	//--------------------------------------------------------------------------------------------------------------------------------------
	public void visualizarPDF() {
		view.viewReport(print, false);
		
	}
	
}
