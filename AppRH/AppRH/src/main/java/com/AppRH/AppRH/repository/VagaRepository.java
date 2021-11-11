package com.AppRH.AppRH.repository;

import org.springframework.data.repository.CrudRepository;

import com.AppRH.AppRH.models.Vaga;

public interface VagaRepository extends CrudRepository<Vaga, String> {
	Vaga findByCodigo(long codigo);
}
