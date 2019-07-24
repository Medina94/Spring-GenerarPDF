package com.pdf;

import org.springframework.stereotype.Service;

@Service
public class MailServicioImpl implements MailServicio{

	@Override
	public void enviarMail(Mail mail) {
		EnviarMail send = new EnviarMail();
		send.sendMail(mail);
	}
	
}
