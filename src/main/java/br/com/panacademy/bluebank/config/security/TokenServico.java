package br.com.panacademy.bluebank.config.security;

import br.com.panacademy.bluebank.modelo.usuario.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
public class TokenServico {

    @Value("${forum.jwt.expiration}")
    private String expiracao;

    @Value("${forum.jwt.secret}")
    private String secret;

    public String gerarToken(Authentication authenticate) {
        Date hoje = new Date();

        Usuario logado = (Usuario) authenticate.getPrincipal();

        return Jwts.builder()
                .setIssuer("API Blue Bank Turma 2 Squad 2")
                .setSubject(logado.getId().toString())
                .setIssuedAt(hoje)
                .setExpiration(new Date(hoje.getTime() + Long.parseLong(expiracao)))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();

    }

    public Boolean isTokenValido(String token) {
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        } catch (Exception e){
            return false;
        }

    }

    public Long getIdUsuario(String token) {
        Claims claimsBody = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        return Long.parseLong(claimsBody.getSubject());

    }

    public String recuperarToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if(token == null || !token.startsWith("Bearer ")){
            return null;
        }

        return token.substring(7);
    }
}
