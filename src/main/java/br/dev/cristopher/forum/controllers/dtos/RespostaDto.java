package br.dev.cristopher.forum.controllers.dtos;

import java.time.LocalDateTime;

import br.dev.cristopher.forum.models.Resposta;
import lombok.Getter;

@Getter
public class RespostaDto {

	private Long id;
	private String mensagem;
	private LocalDateTime dataCriacao;
	private String autor;

	public RespostaDto(Resposta resposta) {
		this.id = resposta.getId();
		this.mensagem = resposta.getMensagem();
		this.dataCriacao = resposta.getDataCriacao();
		this.autor = resposta.getAutor().getNome();
	}

}
