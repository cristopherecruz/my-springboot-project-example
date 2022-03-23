package br.dev.cristopher.forum.controllers.dtos.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.dev.cristopher.forum.models.Topico;
import br.dev.cristopher.forum.repositories.TopicoRepository;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AtualizarTopicoForm {

	@NotNull
	@NotEmpty
	private String titulo;

	@NotNull
	@NotEmpty
	private String mensagem;

	public Topico update(Long id, TopicoRepository topicoRepository) {

		Topico topico = topicoRepository.getById(id);
		topico.setTitulo(titulo);
		topico.setMensagem(mensagem);

		return topico;

	}

}
