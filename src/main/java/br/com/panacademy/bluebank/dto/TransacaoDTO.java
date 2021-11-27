package br.com.panacademy.bluebank.dto;

import br.com.panacademy.bluebank.dto.cliente.ClienteDTO;
import br.com.panacademy.bluebank.modelo.Transacao;
import br.com.panacademy.bluebank.modelo.enuns.TipoTransacao;

public class TransacaoDTO {

    private Long id;
    private TipoTransacao tipoTransacao;
    private Double valor;
    private String descricao;
    private ClienteDTO clienteOrigem;
    private ClienteDTO clienteDestino;

    public TransacaoDTO(Transacao transacao) {
        id = transacao.getId();
        tipoTransacao = transacao.getTipoTransacao();
        valor = transacao.getValor();
        descricao = transacao.getDescricao();
    }


}
