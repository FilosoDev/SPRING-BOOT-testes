package com.AppRH.AppRH.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
//import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.NotEmpty;

@Entity
public class Candidato {

	@Id
	@NotEmpty
	private String rg;
	
	@NotEmpty
	private String nomeCandidato;
	
	@ManyToOne
	private Vaga vaga;
	
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public String nomeCandidato() {
		return nomeCandidato;
	}
	public void setNomeCandidato(String nomeCandidato) {
		this.nomeCandidato = nomeCandidato;
	}
	public Vaga getVaga() {
		return vaga;
	}
	public void setVaga(Vaga vaga) {
		this.vaga = vaga;
	}
	
	
	
	
}
