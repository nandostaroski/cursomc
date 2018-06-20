package com.staroski.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.staroski.cursomc.domain.Categoria;
import com.staroski.cursomc.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repository;
	
	public Categoria buscar(Integer id){
		return repository.findById(id).orElse(null);
	}

}
