package com.staroski.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.staroski.cursomc.domain.Pedido;
import com.staroski.cursomc.repositories.PedidoRepository;
import com.staroski.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repository;

	public Pedido buscar(Integer id) {
		return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado. ID:" + id + ", Tipo:" + Pedido.class.getName()));
	}

}
