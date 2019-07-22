package com.pdf;

import org.springframework.stereotype.Service;

@Service
public class MailServicioImpl implements MailServicio{

	@Override
	public void enviarMail() {
		EnviarMail mail = new EnviarMail();
		mail.sendMail();
	}
	
}
