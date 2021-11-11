package com.rhapp.rhapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.rhapp.rhapp.models.Convidado;
import com.rhapp.rhapp.models.Evento;

public interface ConvidadoRepository extends CrudRepository<Convidado, String> {

	Iterable<Convidado> findByEvento(Evento evento);
	Convidado findByRg(String rg);
	
}
