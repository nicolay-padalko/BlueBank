package br.com.panacademy.bluebank.dto.transacao;

import br.com.panacademy.bluebank.dto.TransacaoDTO;
import br.com.panacademy.bluebank.modelo.Transacao;
import br.com.panacademy.bluebank.modelo.enuns.TipoTransacao;

public class DepositarDTO {

    private Double valor;
    private String descricao;
    private TipoTransacao tipoTransacao;

    public DepositarDTO(Transacao transacao){
        this.valor = transacao.getValor();
        this.descricao = transacao.getDescricao();
        this.tipoTransacao = transacao.getTipoTransacao();
    }

    public DepositarDTO() {
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

    public TipoTransacao getTipoTransacao() {
        return tipoTransacao;
    }

}
