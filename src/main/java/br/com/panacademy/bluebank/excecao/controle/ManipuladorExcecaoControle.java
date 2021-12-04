package br.com.panacademy.bluebank.excecao.controle;

import br.com.panacademy.bluebank.excecao.RecursoNaoEncontradoException;
import br.com.panacademy.bluebank.excecao.SaldoInsuficienteException;
import br.com.panacademy.bluebank.excecao.modelo.ErroModelo;
import br.com.panacademy.bluebank.excecao.modelo.ErroValidacao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ManipuladorExcecaoControle {


    private ErroModelo getErroModelo(Exception e, HttpServletRequest requisicao, HttpStatus status, ErroModelo erro) {
        erro.setTimestamp(Instant.now());
        erro.setStatus(status.value());
        erro.setError("O recurso não foi encontrado");
        erro.setMessage(e.getMessage());
        erro.setPath(requisicao.getRequestURI());

        return erro;
    }

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<ErroModelo> ManipuladorRecursoNaoEncontrado(RuntimeException e, HttpServletRequest requisicao) {
        ErroModelo erroModelo = new ErroModelo();
        HttpStatus status = HttpStatus.NOT_FOUND;

        return ResponseEntity.status(status).body(getErroModelo(e, requisicao, status, erroModelo));
    }

    @ExceptionHandler(SaldoInsuficienteException.class)
    public ResponseEntity<ErroModelo> ManipuladorSaldoInsuficiente(RuntimeException e, HttpServletRequest requisicao) {
        ErroModelo erroModelo = new ErroModelo();
        HttpStatus status = HttpStatus.NOT_FOUND;

        return ResponseEntity.status(status).body(getErroModelo(e, requisicao, status, erroModelo));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroValidacao> ErroValidacao(MethodArgumentNotValidException e, HttpServletRequest requisicao) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        ErroValidacao erro = new ErroValidacao(getErroModelo(e, requisicao, status, new ErroModelo()));

        for (FieldError f : e.getBindingResult().getFieldErrors()){
            erro.adicionarErros(f.getField(), f.getDefaultMessage());
        }
        return ResponseEntity.status(status).body(erro);
    }

}
