package br.com.panacademy.bluebank.DTOs;

import br.com.panacademy.bluebank.modelo.Conta;

import java.math.BigDecimal;
import java.time.Instant;

public class ContaDto {

    public Long id;
    public Integer numeroConta;
    public BigDecimal saldo;
    private Instant dataCriacao;

    public ContaDto(Conta conta) {
        id = conta.getId();
        numeroConta = conta.getNumeroConta();
        saldo = conta.getSaldo();
    }
}
