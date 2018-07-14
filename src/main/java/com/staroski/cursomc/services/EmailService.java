package com.staroski.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.staroski.cursomc.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmactionEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
}
