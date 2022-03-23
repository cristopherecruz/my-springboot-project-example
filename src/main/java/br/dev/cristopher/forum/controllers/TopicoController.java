package br.dev.cristopher.forum.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.dev.cristopher.forum.controllers.dtos.TopicoDetalhadoDto;
import br.dev.cristopher.forum.controllers.dtos.TopicoDto;
import br.dev.cristopher.forum.controllers.dtos.form.AtualizarTopicoForm;
import br.dev.cristopher.forum.controllers.dtos.form.TopicoForm;
import br.dev.cristopher.forum.models.Topico;
import br.dev.cristopher.forum.repositories.CursoRepository;
import br.dev.cristopher.forum.repositories.TopicoRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@AllArgsConstructor
@RequestMapping("/topicos")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TopicoController {

	TopicoRepository topicoRepository;

	CursoRepository cursoRepository;

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

	@GetMapping("/{id}")
	public ResponseEntity<TopicoDetalhadoDto> detalhar(@PathVariable Long id) {

		Optional<Topico> topico = topicoRepository.findById(id);

		if (topico.isPresent()) {
			topicoRepository.deleteById(id);

			return ResponseEntity.ok(new TopicoDetalhadoDto(topicoRepository.findById(id).get()));
		}

		return ResponseEntity.notFound().build();
	}

	@Transactional
	@PutMapping("/{id}")
	public ResponseEntity<TopicoDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizarTopicoForm topicoForm) {

		Optional<Topico> topico = topicoRepository.findById(id);

		if (topico.isPresent()) {
			Topico topicoAtualizado = topicoForm.update(id, topicoRepository);

			return ResponseEntity.ok(new TopicoDto(topicoAtualizado));
		}

		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id) {

		Optional<Topico> topico = topicoRepository.findById(id);

		if (topico.isPresent()) {
			topicoRepository.deleteById(id);

			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}

}
