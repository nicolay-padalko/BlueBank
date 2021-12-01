package br.com.panacademy.bluebank.excecao.controle;

import br.com.panacademy.bluebank.excecao.RecursoNaoEncontradoException;
import br.com.panacademy.bluebank.excecao.modelo.ErroModelo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ManipuladorExcecaoControle {

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<ErroModelo> ManipuladorRecursoNaoEncontrado(RecursoNaoEncontradoException e, HttpServletRequest requisicao) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        ErroModelo erro = new ErroModelo();
        erro.setTimestamp(Instant.now());
        erro.setStatus(status.value());
        erro.setError("O recurso não foi encontrado");
        erro.setMessage(e.getMessage());
        erro.setPath(requisicao.getRequestURI());

        return ResponseEntity.status(status).body(erro);
    }
}
