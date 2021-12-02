package br.dev.cristopher.forum.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.dev.cristopher.forum.controllers.dtos.TopicoDto;
import br.dev.cristopher.forum.models.Topico;
import br.dev.cristopher.forum.repositories.TopicoRepository;

@RestController
public class TopicoController {

	@Autowired
	private TopicoRepository topicoRepository;
	
	@RequestMapping("/topicos")
	public List<TopicoDto> listarTopicos(String nomeCurso) {
		
		List<Topico> topicos = topicoRepository.findAll();
		
		return TopicoDto.converter(topicos);
	}
	
}
