package br.com.panacademy.bluebank.controle;

import br.com.panacademy.bluebank.config.security.TokenServico;
import br.com.panacademy.bluebank.dto.LoginDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("auth")
public class AutenticacaoControle {

    private final AuthenticationManager authenticationManager;
    private final TokenServico tokenServico;

    public AutenticacaoControle(AuthenticationManager authenticationManager, TokenServico tokenServico) {
        this.authenticationManager = authenticationManager;
        this.tokenServico = tokenServico;
    }
    @PostMapping
    public ResponseEntity<LoginDTO> autenticar(@RequestBody @Valid LoginDTO login){
        UsernamePasswordAuthenticationToken dadosLogin =
                new UsernamePasswordAuthenticationToken(login.getEmail(), login.getSenha());
        try {
            Authentication authenticate = authenticationManager.authenticate(dadosLogin);
            String token = tokenServico.gerarToken(authenticate);
            System.out.println("CHRYSCHRYS" + token);
            return ResponseEntity.ok().build();
        } catch (AuthenticationException e){
            return ResponseEntity.badRequest().build();
        }
    }

}
