package com.pdf;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.swing.JOptionPane;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class TestReporte {

	public static void main(String[] args) {
		
		FileInputStream input = null;
		JasperPrint print = null;
		PersonaDataSource data = new PersonaDataSource();
		
		for(int i=0; i < 56; i++) {
			PersonaImpl per;
			per = new PersonaImpl(""+i, "Medina");
			data.agregarPersona(per);
		}
		//-------------------------------------------------------
		// llamo al reporte
		try {
			input = new FileInputStream("reporte/Reporte.jrxml");
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "error 1"+e.getMessage());
		}
		
		try {
			JasperDesign design = JRXmlLoader.load(input);
			JasperReport report = JasperCompileManager.compileReport(design);
			print = JasperFillManager.fillReport(report, null, data);
			
			JasperExportManager.exportReportToPdfFile(print, "reporte/Reporte.pdf");
		} catch (JRException e) {
			JOptionPane.showMessageDialog(null, "error"+e.getMessage());
		}
		
		JasperViewer view = null;
		view.viewReport(print, false);

	}

}
