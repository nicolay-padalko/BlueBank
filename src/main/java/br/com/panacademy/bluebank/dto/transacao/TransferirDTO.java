package br.com.panacademy.bluebank.dto.transacao;

import br.com.panacademy.bluebank.modelo.Transacao;
import br.com.panacademy.bluebank.modelo.enuns.TipoTransacao;

public class TransferirDTO extends OperacaoDTO {

    private Long idContaOrigem;

    private Long idContaDestino;


    public TransferirDTO() {
    }

    public TransferirDTO(Double valor, Double saldo, String descricao, TipoTransacao tipoTransacao, Long idContaOrigem, Long idContaDestino) {
        super(valor, saldo, descricao, tipoTransacao);
        this.idContaOrigem = idContaOrigem;
        this.idContaDestino = idContaDestino;
    }

    public TransferirDTO(Transacao transacao) {
        super(transacao);
    }

    public TransferirDTO(Transacao transacao, Double saldo, Long idContaOrigem, Long idContaDestino) {
        super(transacao, saldo);
        this.idContaOrigem = idContaOrigem;
        this.idContaDestino = idContaDestino;
    }

    public TransferirDTO(OperacaoEntradaDTO operacao) {
        super(operacao);
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