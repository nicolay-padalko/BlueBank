package br.com.panacademy.bluebank.DTOs;

import br.com.panacademy.bluebank.modelo.Conta;
import br.com.panacademy.bluebank.modelo.Transacao;

public class TransacaoDto {

        private Long id;
        private String tipoTransacao;
        private Conta contaOrigem;
        private Conta contaDestino;
        private Double valor;
        private String descricao;

        public TransacaoDto(Transacao transacao) {
            id = transacao.getId();
            tipoTransacao = transacao.getTipoTransacao();
            contaOrigem = transacao.getContaOrigem();
            contaDestino = transacao.getContaDestino();
            valor = transacao.getValor();
            descricao = transacao.getDescricao();
        }
}
