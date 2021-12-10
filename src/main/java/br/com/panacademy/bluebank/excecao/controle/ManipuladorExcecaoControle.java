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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
@CrossOrigin(origins = "*")
public class ManipuladorExcecaoControle {

    private ErroModelo getErroModelo(Exception e, HttpServletRequest requisicao, HttpStatus status, String mensagem) {
        ErroModelo erro = new ErroModelo();
        erro.setTimestamp(Instant.now());
        erro.setStatus(status.value());
        erro.setError(mensagem);
        erro.setMessage(e.getMessage());
        erro.setPath(requisicao.getRequestURI());

        return erro;
    }

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<ErroModelo> ManipuladorRecursoNaoEncontrado(RecursoNaoEncontradoException e, HttpServletRequest requisicao) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        return ResponseEntity.status(status).body(getErroModelo(e, requisicao, status,"O recurso não foi encontrado"));
    }

    @ExceptionHandler(SaldoInsuficienteException.class)
    public ResponseEntity<ErroModelo> ManipuladorSaldoInsuficiente(SaldoInsuficienteException e, HttpServletRequest requisicao) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        return ResponseEntity.status(status).body(getErroModelo(e, requisicao, status, "O saldo informado é insuficiente"));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErroModelo> ManipuladorArgumentoNaoEsperado(MethodArgumentTypeMismatchException e, HttpServletRequest requisicao) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(getErroModelo(e, requisicao, status, "O argumento passado não era esperado"));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroValidacao> ErroValidacao(MethodArgumentNotValidException e, HttpServletRequest requisicao) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        ErroValidacao erro = new ErroValidacao(getErroModelo(e, requisicao, status, "Argumentos não válidos."));

        for (FieldError f : e.getBindingResult().getFieldErrors()){
            erro.adicionarErros(f.getField(), f.getDefaultMessage());
        }
        return ResponseEntity.status(status).body(erro);
    }

}
