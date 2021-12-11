package br.com.panacademy.bluebank.dto.transacao;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;

public class OperacaoEntradaDTO {

    @DecimalMin(value = "0.0", inclusive = false)
    private Double valor;

    @Size(max=200)
    private String descricao;

    public OperacaoEntradaDTO(Double valor, String descricao) {
        this.valor = valor;
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
