package br.com.panacademy.bluebank.servico;

import br.com.panacademy.bluebank.DTOs.TransacaoDto;
import br.com.panacademy.bluebank.repositorio.ContaRepositorio;
import br.com.panacademy.bluebank.repositorio.TransacaoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class TransacaoServico {

    @Autowired
    private TransacaoRepositorio transacaoRepositorio;

    @Autowired
    private ContaRepositorio contaRepositorio;


}
