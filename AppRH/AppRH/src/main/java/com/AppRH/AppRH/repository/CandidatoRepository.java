package com.AppRH.AppRH.repository;

import org.springframework.data.repository.CrudRepository;

import com.AppRH.AppRH.models.Candidato;
import com.AppRH.AppRH.models.Vaga;

public interface CandidatoRepository extends CrudRepository<Candidato, String> {

	Iterable<Candidato> findByVaga(Vaga vaga);
	Candidato findByRg(String rg);
	
}
