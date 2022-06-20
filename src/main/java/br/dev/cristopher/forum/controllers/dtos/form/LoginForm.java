package br.dev.cristopher.forum.controllers.dtos.form;

import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Setter
public class LoginForm {

    private String email;
    private String senha;

    public UsernamePasswordAuthenticationToken converter(LoginForm form) {
        return new UsernamePasswordAuthenticationToken(form.email, form.senha);
    }

}
