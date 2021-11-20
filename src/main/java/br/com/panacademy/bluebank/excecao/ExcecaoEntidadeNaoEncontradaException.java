package br.com.panacademy.bluebank.excecao;

public class ExcecaoEntidadeNaoEncontradaException extends RuntimeException {

    public ExcecaoEntidadeNaoEncontradaException(String mensagem) {
        super(mensagem);
    }
}