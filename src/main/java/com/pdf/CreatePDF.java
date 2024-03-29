package com.pdf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

import org.apache.lucene.index.MergePolicy;

import com.lowagie.text.pdf.PdfDocument;
import com.lowagie.text.pdf.PdfReader;

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
			input = new FileInputStream("reporte/Reporte.jrxml");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	//--------------------------------------------------------------------------------------------------------------------------------------
	public byte[] crearReporte() {
		try {
			design = JRXmlLoader.load(input);
			report = JasperCompileManager.compileReport(design);
			print = JasperFillManager.fillReport(report, null, data);
			
			// Exporto el Pdf pero no lo guardo en disco, sino que lo paso a byte
			byte[] b = JasperExportManager.exportReportToPdf(print);
			return b;
			//GuardarPDFenRuta();
		} catch (JRException e) {
			e.printStackTrace();
		}
		return new byte[] {};
		
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
	//--------------------------------------------------------------------------------------------------------------------------------------
	public byte[] pdfBinario() throws IOException {
		//byte[] b = null;
		/*traerReporte();
		crearReporte();
		File file = new File("C:\\Users\\cmedina\\Desktop\\ReportePersona2.pdf");
		byte[] bytes = Files.readAllBytes(file.toPath());
		*/
		// Antes traia un Pdf desde un ruta del disco. 
		// Ahora retorno un byte[] que me devuelve el metodo crear reporte
		return crearReporte();
	}
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	
	
}
