package com.rhapp.rhapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.rhapp.rhapp.models.Convidado;
import com.rhapp.rhapp.models.Evento;
import com.rhapp.rhapp.repository.ConvidadoRepository;
import com.rhapp.rhapp.repository.EventoRepository;

@Controller
public class EventoController {

	@Autowired
	private EventoRepository er;
	
	@Autowired
	private ConvidadoRepository cr;
	
	@RequestMapping(value = "/cadastrarEvento", method=RequestMethod.GET)
	public String form() {
		return "evento/formEvento";
		
	}
	
	@RequestMapping(value = "/cadastrarEvento", method=RequestMethod.POST)
	public String form(Evento evento) {
		
		er.save(evento);
		
		return "redirect:/cadastrarEvento";
		
	}
	
	
	// lista os eventos
	@RequestMapping("/eventos")
	public ModelAndView listaEventos () {
		ModelAndView mv = new ModelAndView("index");
		Iterable<Evento> eventos = er.findAll();
		mv.addObject("eventos", eventos);
		return mv;
	}
	
	
	// pega o código e faz a busca no banco
	@RequestMapping(value="/{codigo}", method=RequestMethod.GET)
	public ModelAndView detalhesEvento(@PathVariable("codigo") long codigo) {
		Evento evento = er.findByCodigo(codigo);
		ModelAndView mv = new ModelAndView("evento/detalhesEvento");
		mv.addObject("evento", evento);
		
		Iterable<Convidado> convidados = cr.findByEvento(evento);
		mv.addObject("convidados", convidados);
		
		return mv;
	}
	
	// adiciona convidado e lista todos do evento
	@RequestMapping(value="/{codigo}", method=RequestMethod.POST)
	public String detalhesEventoPost(@PathVariable("codigo") long codigo, Convidado convidado) {
		Evento evento = er.findByCodigo(codigo);
		convidado.setEvento(evento);
		cr.save(convidado);	
		return "redirect:/{codigo}";
	}
	
	
	
	
}
