package br.com.panacademy.bluebank.dto.transacao;

import br.com.panacademy.bluebank.modelo.Transacao;
import br.com.panacademy.bluebank.modelo.enuns.TipoTransacao;

public class SacarDTO {


    private Double valor;
    private Double saldo;
    private String descricao;
    private TipoTransacao tipoTransacao;

    public SacarDTO() {
    }



    public SacarDTO(Transacao transacao, Double saldo){
        this.valor = transacao.getValor();
        this.saldo = saldo;
        this.descricao = transacao.getDescricao();
        this.tipoTransacao = transacao.getTipoTransacao();
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
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
