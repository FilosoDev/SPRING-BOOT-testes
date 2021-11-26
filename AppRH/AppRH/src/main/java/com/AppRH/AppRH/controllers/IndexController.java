package com.AppRH.AppRH.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.AppRH.AppRH.models.Candidato;
import com.AppRH.AppRH.models.Dependentes;
import com.AppRH.AppRH.models.Funcionario;
import com.AppRH.AppRH.models.Vaga;
import com.AppRH.AppRH.repository.CandidatoRepository;
import com.AppRH.AppRH.repository.DependentesRepository;
import com.AppRH.AppRH.repository.FuncionarioRepository;
import com.AppRH.AppRH.repository.VagaRepository;

@Controller
public class IndexController {

	@Autowired
	private FuncionarioRepository fr;

	@Autowired
	private DependentesRepository dr;

	@Autowired
	private CandidatoRepository cr;

	@Autowired
	private VagaRepository vr;

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@PostMapping("/")
	public ModelAndView pesquisarFuncionario(@RequestParam("nome") String nome, @RequestParam("busca") String busca) {

		String contador = "Resultado: ";
		int contadorint = 0;

		ModelAndView mv = new ModelAndView("index");

		if (busca.equals("nomefuncionario") || busca.equals("todos")) {

			Funcionario funcionario = fr.findByNome(nome);

			mv.addObject("funcionario", funcionario);

			Iterable<Dependentes> dependentes = dr.findByFuncionario(funcionario);
			mv.addObject("dependentes", dependentes);

			contadorint = contadorint + ((funcionario != null) ? 1 : 0);

			mv.addObject("isfunc", ((funcionario != null) ? true : false));

		}

		if (busca.equals("nomedependente") || busca.equals("todos")) {

			List<Dependentes> dependentes2 = dr.findByNomeContaining(nome);

			mv.addObject("dependentes2", dependentes2);
			contadorint = contadorint + (dependentes2.size());

			mv.addObject("isdependentes2", (dependentes2.size() > 0) ? true : false);

		}

		if (busca.equals("nomecandidato") || busca.equals("todos")) {

			List<Candidato> candidatos = cr.findBynomeCandidato(nome);

			contadorint = contadorint + (candidatos.size());
			mv.addObject("candidatos", candidatos);

			mv.addObject("iscandidatos", (candidatos.size() > 0) ? true : false);
		}

		if (busca.equals("titulovaga") || busca.equals("todos")) {

			List<Vaga> vagas = vr.findByNome(nome);

			mv.addObject("vagas", vagas);

			contadorint = contadorint + (vagas.size());

			mv.addObject("isvagas", (vagas.size() > 0) ? true : false);
		}

		if (!busca.equals("todos"))
			busca = (busca.indexOf("nome") >= 0) ? busca.replace("nome", "nome do ") : "título da vaga";
		busca = "Resultados da pesquisa por " + busca;
		mv.addObject("busca", busca);

		contador = contador + "" + contadorint;
		mv.addObject("contadormsg", contador);
		mv.addObject("contador", contadorint);

		return mv;
	}

}
