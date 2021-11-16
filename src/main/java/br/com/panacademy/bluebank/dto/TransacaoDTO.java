package br.com.panacademy.bluebank.dto;

import br.com.panacademy.bluebank.modelo.enuns.TipoTransacao;
import br.com.panacademy.bluebank.modelo.Conta;
import br.com.panacademy.bluebank.modelo.Transacao;

public class TransacaoDTO {

    private Long id;
    private TipoTransacao tipoTransacao;
    private Double valor;
    private String descricao;

    public TransacaoDTO(Transacao transacao) {
        id = transacao.getId();
        tipoTransacao = transacao.getTipoTransacao();
        valor = transacao.getValor();
        descricao = transacao.getDescricao();
    }


}
