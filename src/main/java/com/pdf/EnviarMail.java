package com.pdf;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;



public class EnviarMail {
	@Autowired
	private Mail mail;
	
	
	public Mail getMail() {
		return mail;
	}
	public void setMail(Mail mail) {
		this.mail = mail;
	}

//-------------------------------------------------------------------------------------------------------
	public void sendMail(Mail dato) {
		setMail(dato);
		try
        {
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.port", "587");
            props.setProperty("mail.smtp.user", "cristian.medina94@gmail.com");
            props.setProperty("mail.smtp.auth", "true");

            Session session = Session.getDefaultInstance(props, null);
            // session.setDebug(true);

            // Se compone la parte del texto
            BodyPart texto = new MimeBodyPart();
            texto.setText(mail.getMensaje());

            // Se compone el adjunto con la imagen
            BodyPart adjunto = new MimeBodyPart();
            adjunto.setDataHandler(new DataHandler(new FileDataSource("C:\\Users\\cmedina\\Desktop\\ReportePersona2.pdf")));
           
            adjunto.setFileName("ReportePersona2.pdf");

            // Una MultiParte para agrupar texto e imagen.
            MimeMultipart multiParte = new MimeMultipart();
            multiParte.addBodyPart(texto);
            multiParte.addBodyPart(adjunto);

            // Se compone el correo, dando to, from, subject y el
            // contenido.
            MimeMessage message = new MimeMessage(session);
            //e-mail del emisor
            message.setFrom(new InternetAddress(mail.getEmisor()));
            //e-mail del receptor
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(mail.getReceptor()));
            //Asunto del mail
            message.setSubject(mail.getAsunto());
            //seteo el contenido del mail con el texto y el archivo adjunto
            message.setContent(multiParte);

            // Se envia el correo.
            Transport t = session.getTransport("smtp");
            t.connect("cristian.medina94@gmail.com", "wrwyqsbpfhrnoals");
            t.sendMessage(message, message.getAllRecipients());
            t.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    
	}

}
