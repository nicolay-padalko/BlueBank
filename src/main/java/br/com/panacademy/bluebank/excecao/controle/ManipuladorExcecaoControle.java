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


    private ResponseEntity<ErroModelo> getErroModeloResponseEntity(RuntimeException e, HttpServletRequest requisicao, HttpStatus status, String mensagemErro) {
        ErroModelo erro = new ErroModelo();
        erro.setTimestamp(Instant.now());
        erro.setStatus(status.value());
        erro.setError(mensagemErro);
        erro.setMessage(e.getMessage());
        erro.setPath(requisicao.getRequestURI());

        return ResponseEntity.status(status).body(erro);
    }

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<ErroModelo> ManipuladorRecursoNaoEncontrado(RuntimeException e, HttpServletRequest requisicao) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        return getErroModeloResponseEntity(e, requisicao, status, "O recuso nao foi encontrado");
    }

    @ExceptionHandler(SaldoInsuficienteException.class)
    public ResponseEntity<ErroModelo> ManipuladorSaldoInsuficiente(RuntimeException e, HttpServletRequest requisicao) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        return getErroModeloResponseEntity(e, requisicao, status, "O saldo nao e suficiente");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroValidacao> ErroValidacao(MethodArgumentNotValidException e, HttpServletRequest requisicao) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErroValidacao erro = new ErroValidacao();
        erro.setTimestamp(Instant.now());
        erro.setStatus(status.value());
        erro.setError("Erro na validação dos campos");
        erro.setMessage(e.getMessage());
        erro.setPath(requisicao.getRequestURI());

        for (FieldError f : e.getBindingResult().getFieldErrors()){
            erro.adicionarErros(f.getField(), f.getDefaultMessage());
        }
        return ResponseEntity.status(status).body(erro);
    }

}
