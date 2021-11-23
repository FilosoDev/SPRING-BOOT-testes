package com.AppRH.AppRH.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.AppRH.AppRH.models.Dependentes;
import com.AppRH.AppRH.models.Funcionario;
import com.AppRH.AppRH.repository.DependentesRepository;
import com.AppRH.AppRH.repository.FuncionarioRepository;

//import antlr.collections.List;
import java.util.List;

@Controller
public class PesquisarController {

	@Autowired
	private FuncionarioRepository fr;

	@Autowired
	private DependentesRepository dr;

	@RequestMapping("/buscar")
	public ModelAndView listaPesquisar() {
		ModelAndView mv = new ModelAndView("funcionario/pesquisar");
		return mv;
	}

	@PostMapping("/buscar")
	public ModelAndView pesquisarFuncionario(@RequestParam("nome") String nome, @RequestParam("busca") String busca) {

		ModelAndView mv = new ModelAndView("funcionario/pesquisar");

		//System.out.println("---------------" + nome);
		
		if (busca.equals("nomefuncionario")) {

			Funcionario funcionario = fr.findByNome(nome);
			mv.addObject("funcionario", funcionario);

			Iterable<Dependentes> dependentes = dr.findByFuncionario(funcionario);
			mv.addObject("dependentes", dependentes);
			
			//System.out.println("---------------" + funcionario.getNome());

			mv.addObject("contador", (funcionario != null) ? 1 : 0);
			
			//System.out.println("---------------" + nome);
		}

		if (busca.equals("nomedependente")) {

		List<Dependentes> dependentes = dr.findByNome(nome);
		mv.addObject("lista", dependentes);

		mv.addObject("contador", dependentes.size());

		 }

		// Nome do Candidatos
		// if (busca.equals("nomecandidato")) {

//					List<Candidato> candidatos = REPOSITORY.findByNome(nome);
//					mv.addObject("lista", candidatos);

//					mv.addObject("contador", candidatos.size());

		// }

		// if (busca.equals("nomevaga")) {
//
		// }

		busca = (busca.indexOf("nome") >= 0) ? busca.replace("nome", "nome do ") : "título da vaga";
		mv.addObject("busca", busca);

		return mv;
	}

}