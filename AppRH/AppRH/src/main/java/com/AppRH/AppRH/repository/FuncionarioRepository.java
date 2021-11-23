package com.AppRH.AppRH.repository;

import org.springframework.data.repository.CrudRepository;

import com.AppRH.AppRH.models.Funcionario;
import com.AppRH.AppRH.models.Dependentes;

public interface FuncionarioRepository extends CrudRepository<Funcionario, String> {

	Funcionario findById(long id);

	// criado para a busca
	Funcionario findByNome(String nome);
}
