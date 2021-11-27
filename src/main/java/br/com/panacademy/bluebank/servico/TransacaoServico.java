package br.com.panacademy.bluebank.servico;

import br.com.panacademy.bluebank.dto.TransacaoDTO;
import br.com.panacademy.bluebank.dto.transacao.DepositarDTO;
import br.com.panacademy.bluebank.excecao.RecursoNaoEncontradoException;
import br.com.panacademy.bluebank.modelo.Cliente;
import br.com.panacademy.bluebank.modelo.Conta;
import br.com.panacademy.bluebank.modelo.Transacao;
import br.com.panacademy.bluebank.modelo.enuns.TipoTransacao;
import br.com.panacademy.bluebank.repositorio.ClienteRepositorio;
import br.com.panacademy.bluebank.repositorio.ContaRepositorio;
import br.com.panacademy.bluebank.repositorio.TransacaoRepositorio;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class TransacaoServico {

    private final TransacaoRepositorio transacaoRepositorio;
    private final ContaRepositorio contaRepositorio;
    private final ClienteRepositorio clienteRepositorio;

    public TransacaoServico(TransacaoRepositorio transacaoRepositorio, ContaRepositorio contaRepositorio, ClienteRepositorio clienteRepositorio) {
        this.transacaoRepositorio = transacaoRepositorio;
        this.contaRepositorio = contaRepositorio;
        this.clienteRepositorio = clienteRepositorio;
    }

    @Transactional
    public DepositarDTO depositar(Long id, DepositarDTO dto) {
        Cliente cliente = clienteRepositorio.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente nao encontrado: " + id));

        Double valordeposito = dto.getValor() + cliente.getConta().getSaldo();
        cliente.getConta().setSaldo(valordeposito);

        Transacao transacao = new Transacao();
        transacao.setTipoTransacao(TipoTransacao.DEPOSITO);
        transacao.setDescricao(dto.getDescricao());
        transacao.setValor(dto.getValor());
        transacao.setConta(cliente.getConta());

        cliente.getConta().getTransacoes().add(transacaoRepositorio.save(transacao));

        clienteRepositorio.save(cliente);

        return new DepositarDTO(transacao);

    }

}
