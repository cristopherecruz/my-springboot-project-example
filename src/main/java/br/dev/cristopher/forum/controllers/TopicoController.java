package br.dev.cristopher.forum.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.dev.cristopher.forum.controllers.dtos.TopicoDto;
import br.dev.cristopher.forum.controllers.dtos.form.TopicoForm;
import br.dev.cristopher.forum.models.Topico;
import br.dev.cristopher.forum.repositories.CursoRepository;
import br.dev.cristopher.forum.repositories.TopicoRepository;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

	@Autowired
	private TopicoRepository topicoRepository;
	
	@Autowired
	private CursoRepository cursoRepository;
	
	@GetMapping
	public List<TopicoDto> listarTopicos(String nomeCurso) {
		
		if (nomeCurso == null) {
			List<Topico> topicos = topicoRepository.findAll();
			
			return TopicoDto.converter(topicos);
		}
		
		List<Topico> topicos = topicoRepository.findByCursoNome(nomeCurso);
		
		return TopicoDto.converter(topicos);
	}
	
	@PostMapping
	public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicoForm topicoForm, UriComponentsBuilder uriBuilder) {
		
		Topico novoTopico = topicoForm.converter(cursoRepository); 
		
		topicoRepository.save(novoTopico);
		
		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(novoTopico.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new TopicoDto(novoTopico));
		
	}
	
}
