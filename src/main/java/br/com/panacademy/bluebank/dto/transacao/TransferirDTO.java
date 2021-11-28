package br.com.panacademy.bluebank.dto.transacao;

import br.com.panacademy.bluebank.modelo.Transacao;

public class TransferirDTO {

    private Double valor;
    private String descricao;
    private Long idContaOrigem;
    private Long idContaDestino;

    public TransferirDTO(Transacao transacao) {
        this.valor = transacao.getValor();
        this.descricao = transacao.getDescricao();
        this.idContaOrigem = transacao.getConta().getContaId();
        this.idContaDestino = transacao.getIdContaDestino();
    }

    public TransferirDTO() {
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

    public Long getIdContaOrigem() {
        return idContaOrigem;
    }

    public void setIdContaOrigem(Long idContaOrigem) {
        this.idContaOrigem = idContaOrigem;
    }

    public Long getIdContaDestino() {
        return idContaDestino;
    }

    public void setIdContaDestino(Long idContaDestino) {
        this.idContaDestino = idContaDestino;
    }
}