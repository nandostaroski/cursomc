package com.staroski.cursomc.services;

import com.staroski.cursomc.domain.Cliente;
import com.staroski.cursomc.repositories.ClienteRepository;
import com.staroski.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AuthService {

    private static final int START_UNICODE_UPPERCASE = 65;
    private static final int START_UNICODE_LOWERCASE = 97;
    private static final int START_UNICODE_NUMBER = 48;
    private static final int ALPHABET_QUANTITY = 26;
    private static final int DIGITS_QUANTITY = 10;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private BCryptPasswordEncoder pe;

    @Autowired
    private EmailService emailService;

    private Random random = new Random();

    public void sendNewPassword(String email) {
        Cliente cliente = clienteRepository.findByEmail(email).orElseThrow(() -> new ObjectNotFoundException("Email não encontrado"));

        String newPass = newPassword();
        cliente.setSenha(pe.encode(newPass));

        clienteRepository.save(cliente);
        emailService.sendNewPasswordEmail(cliente, newPass);
    }

    private String newPassword() {
        char[] vet = new char[10];
        for (int i = 0; i < 10; i++) {
            vet[i] = randomCharAlfaNumerico();
        }
        return new String(vet);
    }

    private char randomCharAlfaNumerico() { //unicode-table.com/pt/
        int opt = random.nextInt(3);
        switch (opt) {
            case 0: {
                return randomCharNumerico();
            }
            case 1: {
                return randomCharLetraMaiuscula();
            }
            case 2: {
                return randomCharLetraMinuscula();
            }
        }
        return (char)0;
    }

    private char randomCharLetraMinuscula() {
        return (char) (random.nextInt(ALPHABET_QUANTITY) + START_UNICODE_LOWERCASE);
    }

    private char randomCharLetraMaiuscula() {
        return (char) (random.nextInt(ALPHABET_QUANTITY) + START_UNICODE_UPPERCASE);
    }

    private char randomCharNumerico() {
        return (char) (random.nextInt(DIGITS_QUANTITY) + START_UNICODE_NUMBER);
    }

}
