package com.staroski.cursomc.services;

import com.staroski.cursomc.domain.Estado;
import com.staroski.cursomc.repositories.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository repository;

    public List<Estado> findAll() {
        return repository.findAllByOrderByNome();
    }
}
