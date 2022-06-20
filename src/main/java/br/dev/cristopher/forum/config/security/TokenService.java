package br.dev.cristopher.forum.config.security;

import br.dev.cristopher.forum.models.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${forum.jwt.expiration}")
    private String expiration;

    @Value("${forum.jwt.secret}")
    private String secret;

    public String gerarToken(Authentication authenticate) {
        Usuario usuario = (Usuario) authenticate.getPrincipal();

        Date hoje = new Date();

        Date expiracao = new Date(hoje.getTime() + Long.parseLong(expiration));

        return Jwts.builder()
                .setIssuer("Api do Forum Cristopher")
                .setSubject(usuario.getId().toString())
                .setIssuedAt(hoje)
                .setExpiration(expiracao)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean isToken(String token) {

        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);

            return true;

        } catch (Exception e) {
            return false;
        }

    }

    public Long getIdUsuario(String token) {

        Claims body = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();

        return Long.parseLong(body.getSubject());

    }
}
