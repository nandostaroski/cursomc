package com.staroski.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.staroski.cursomc.domain.Cliente;
import com.staroski.cursomc.repositories.ClienteRepository;
import com.staroski.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	public Cliente find(Integer id) {
		return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado. ID:" + id + ", Tipo:" + Cliente.class.getName()));
	}

}
