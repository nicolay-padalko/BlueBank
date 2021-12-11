package br.com.panacademy.bluebank.dto.transacao;

import br.com.panacademy.bluebank.modelo.Transacao;
import br.com.panacademy.bluebank.modelo.enuns.TipoTransacao;

public class SacarDTO extends OperacaoDTO {
    public SacarDTO() {
    }

    public SacarDTO(Double valor, Double saldo, String descricao, TipoTransacao tipoTransacao) {
        super(valor, saldo, descricao, tipoTransacao);
    }

    public SacarDTO(OperacaoEntradaDTO operacao){
        super(operacao);
    }

    public SacarDTO(TransferirDTO transferir) {
        super(transferir.getValor());
    }

    public SacarDTO(Transacao transacao, Double saldo) {
        super(transacao, saldo);
    }
}
