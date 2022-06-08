package br.dev.cristopher.forum.controllers;

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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/topicos")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TopicoController {

    TopicoRepository topicoRepository;
    CursoRepository cursoRepository;

    @GetMapping
    public Page<TopicoDto> listarTopicos(@RequestParam(required = false) String nomeCurso, @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable paginacao) {

        if (nomeCurso == null) {
            Page<Topico> topicos = topicoRepository.findAll(paginacao);

            return TopicoDto.converter(topicos);
        }

        Page<Topico> topicos = topicoRepository.findByCursoNome(nomeCurso, paginacao);

        return TopicoDto.converter(topicos);
    }

    @Transactional
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

    @Transactional
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
