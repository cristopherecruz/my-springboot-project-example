package br.dev.cristopher.forum.controllers.validation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomErrorDto {

	private String field;
	private String error;

}
