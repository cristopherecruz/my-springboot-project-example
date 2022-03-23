package br.dev.cristopher.forum.controllers.dtos;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.dev.cristopher.forum.models.StatusTopico;
import br.dev.cristopher.forum.models.Topico;
import lombok.Getter;

@Getter
public class TopicoDetalhadoDto {

	private Long id;
	private String titulo;
	private String mensagem;
	private LocalDateTime dataCriacao;
	private String nomeAutor;
	private StatusTopico status;
	private List<RespostaDto> respostas;

	public TopicoDetalhadoDto(Topico topico) {
		this.id = topico.getId();
		this.titulo = topico.getTitulo();
		this.mensagem = topico.getMensagem();
		this.dataCriacao = topico.getDataCriacao();
		this.nomeAutor = topico.getAutor().getNome();
		this.status = topico.getStatus();
		this.respostas = topico.getRespostas().stream().map(r -> new RespostaDto(r)).collect(Collectors.toList());
	}

}
