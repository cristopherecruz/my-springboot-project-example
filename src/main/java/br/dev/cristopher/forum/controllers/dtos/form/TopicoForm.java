package br.dev.cristopher.forum.controllers.dtos.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.dev.cristopher.forum.models.Curso;
import br.dev.cristopher.forum.models.Topico;
import br.dev.cristopher.forum.repositories.CursoRepository;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TopicoForm {

	@NotNull
	@NotEmpty
	private String titulo;
	
	@NotNull
	@NotEmpty
	private String mensagem;
	
	@NotNull
	@NotEmpty
	private String nomeCurso;

	public Topico converter(CursoRepository cursoRepository) {
		
		Curso curso = cursoRepository.findByNome(nomeCurso);
		
		return new Topico(titulo, mensagem, curso);
	}

}
