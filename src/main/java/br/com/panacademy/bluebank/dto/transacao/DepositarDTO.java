package br.com.panacademy.bluebank.dto.transacao;

import br.com.panacademy.bluebank.modelo.Transacao;
import br.com.panacademy.bluebank.modelo.enuns.TipoTransacao;

public class DepositarDTO extends OperacaoDTO {

    public DepositarDTO() {
    }

    public DepositarDTO(Double valor, Double saldo, String descricao, TipoTransacao tipoTransacao) {
        super(valor, saldo, descricao, tipoTransacao);
    }

    public DepositarDTO(OperacaoEntradaDTO operacao) {
        super(operacao);
    }

    public DepositarDTO(TransferirDTO transferir) {
        super(transferir.getValor());
    }

    public DepositarDTO(Transacao transacao) {
        super(transacao);
    }

    public DepositarDTO(Transacao transacao, Double saldo) {
        super(transacao, saldo);
    }
}
