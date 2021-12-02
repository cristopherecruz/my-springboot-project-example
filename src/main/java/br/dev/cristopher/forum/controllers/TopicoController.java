package br.dev.cristopher.forum.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.dev.cristopher.forum.controllers.dtos.TopicoDto;
import br.dev.cristopher.forum.models.Curso;
import br.dev.cristopher.forum.models.Topico;

@RestController
public class TopicoController {

	@RequestMapping("/topicos")
	public List<TopicoDto> listarTopicos() {
		
		Topico topico = new Topico("Dúvida", "Dúvida com Spring", new Curso("Spring", "Programação"));
		
		return TopicoDto.converter(Arrays.asList(topico, topico, topico));
	}
	
}
