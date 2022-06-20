package br.dev.cristopher.forum.controllers.dtos;

import lombok.Getter;

@Getter
public class TokenDto {
    private final String token;
    private final String bearer;

    public TokenDto(String token, String bearer) {
        this.token = token;
        this.bearer = bearer;
    }
}
