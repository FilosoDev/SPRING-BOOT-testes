package com.rhapp.rhapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.rhapp.rhapp.models.Evento;

public interface EventoRepository extends CrudRepository<Evento, String> {
	Evento findByCodigo(long codigo);
}
