package br.com.panacademy.bluebank.dto;

import br.com.panacademy.bluebank.dto.cliente.ClienteDTO;
import br.com.panacademy.bluebank.modelo.Cliente;
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

    public TransacaoDTO(Transacao transacao, Cliente cliente) {
        id = transacao.getId();
        tipoTransacao = transacao.getTipoTransacao();
        valor = transacao.getValor();
        descricao = transacao.getDescricao();
    }

    public TransacaoDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoTransacao getTipoTransacao() {
        return tipoTransacao;
    }

    public void setTipoTransacao(TipoTransacao tipoTransacao) {
        this.tipoTransacao = tipoTransacao;
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

    public ClienteDTO getClienteOrigem() {
        return clienteOrigem;
    }

    public void setClienteOrigem(ClienteDTO clienteOrigem) {
        this.clienteOrigem = clienteOrigem;
    }

    public ClienteDTO getClienteDestino() {
        return clienteDestino;
    }

    public void setClienteDestino(ClienteDTO clienteDestino) {
        this.clienteDestino = clienteDestino;
    }
}
