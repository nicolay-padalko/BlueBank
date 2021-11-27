package br.com.panacademy.bluebank.servico;

import br.com.panacademy.bluebank.repositorio.ContaRepositorio;
import br.com.panacademy.bluebank.repositorio.TransacaoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TransacaoServico {

    private final TransacaoRepositorio transacaoRepositorio;
    private final ContaRepositorio contaRepositorio;

    public TransacaoServico(TransacaoRepositorio transacaoRepositorio, ContaRepositorio contaRepositorio) {
        this.transacaoRepositorio = transacaoRepositorio;
        this.contaRepositorio = contaRepositorio;
    }



}
