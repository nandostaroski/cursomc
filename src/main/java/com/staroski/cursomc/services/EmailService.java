package com.staroski.cursomc.services;

import com.staroski.cursomc.domain.Pedido;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    void sendOrderConfirmactionEmail(Pedido obj);

    void sendEmail(SimpleMailMessage msg);
}
