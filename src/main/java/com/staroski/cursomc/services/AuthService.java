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
            vet[i] = ramdomChar();
        }
        return new String(vet);
    }

    private char ramdomChar() { //unicode-table.com/pt/
        int opt = random.nextInt(3);
        switch (opt) {
            case 0: { // digito
                return (char) (random.nextInt(10) + 48);
            }
            case 1: { //letra maiuscula
                return (char) (random.nextInt(26) + 65);
            }
            case 2: { //letra minuscula
                return (char) (random.nextInt(26) + 97);
            }
        }
        return (char)0;
    }

}
