package br.com.panacademy.bluebank.dto;

import br.com.panacademy.bluebank.modelo.Conta;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ContaDTO {

    private Long contaId;
    private BigDecimal saldo;
    private LocalDateTime criadoEm;

    public ContaDTO(Conta conta) {
        this.contaId = conta.getContaId();
        this.saldo = conta.getSaldo();
        this.criadoEm = conta.getCriadoEm();
    }

    public Long getContaId() {
        return contaId;
    }

    public void setContaId(Long contaId) {
        this.contaId = contaId;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }
}
