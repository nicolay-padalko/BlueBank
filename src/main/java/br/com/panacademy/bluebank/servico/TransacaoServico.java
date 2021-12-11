package br.com.panacademy.bluebank.servico;

import br.com.panacademy.bluebank.config.security.TokenServico;
import br.com.panacademy.bluebank.dto.transacao.*;
import br.com.panacademy.bluebank.excecao.SaldoInsuficienteException;
import br.com.panacademy.bluebank.modelo.Conta;
import br.com.panacademy.bluebank.modelo.usuario.Cliente;
import br.com.panacademy.bluebank.modelo.Transacao;
import br.com.panacademy.bluebank.modelo.enuns.TipoTransacao;
import br.com.panacademy.bluebank.repositorio.TransacaoRepositorio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@Service
public class TransacaoServico {

    private final TransacaoRepositorio transacaoRepositorio;
    private final ClienteServico clienteServico;
    private final TokenServico tokenServico;
    private final ContaServico contaServico;

    public TransacaoServico(TransacaoRepositorio transacaoRepositorio, ClienteServico clienteServico, TokenServico tokenServico, ContaServico contaServico) {
        this.transacaoRepositorio = transacaoRepositorio;
        this.clienteServico = clienteServico;
        this.tokenServico = tokenServico;
        this.contaServico = contaServico;
    }

    public Page<Transacao> listarTodos(HttpServletRequest request, PageRequest pageRequest){
        String token = tokenServico.recuperarToken(request);
        Long idUsuario = tokenServico.getIdUsuario(token);
        String tipo = clienteServico.identificaTipoPorId(idUsuario);
        List<Transacao> listaTransacoes = new ArrayList<>();
        if(tipo.equals("ADMIN")) {
            return transacaoRepositorio.findAll(pageRequest);
        }else if(tipo.equals("CLIENTE")){
            Conta conta = contaServico.filtrarContaPorIdUsuario(idUsuario);
            listaTransacoes = transacaoRepositorio.findAllByContaUsuario(conta.getContaId());
        }
        return new PageImpl<>(listaTransacoes);
    }


    @Transactional
    public DepositarDTO depositar(Long contaId, OperacaoEntradaDTO operacao) {

        Cliente cliente = clienteServico.filtrarClientePorContaId(contaId);
        DepositarDTO depositar = new DepositarDTO(operacao);
        cliente.getConta().setSaldo(retornoSaldoDeposito(cliente, depositar));

        List<Cliente> listaCliente = new ArrayList<>();
        listaCliente.add(cliente);

        return operacao(depositar, listaCliente, TipoTransacao.DEPOSITO);
    }

    @Transactional
    public SacarDTO sacar(Long contaId, OperacaoEntradaDTO operacao){
        Cliente cliente = clienteServico.filtrarClientePorContaId(contaId);
        SacarDTO sacar = new SacarDTO(operacao);
        cliente.getConta().setSaldo(retornoSaldoSaque(cliente, sacar));
        List<Cliente> listaCliente = new ArrayList<>();
        listaCliente.add(cliente);
        return operacao(sacar, listaCliente, TipoTransacao.SAQUE);
    }


    @Transactional
    public TransferirDTO transferir(Long idOrigem, Long idDestino, OperacaoEntradaDTO transferir) {
        Cliente clienteOrigem = clienteServico.filtrarClientePorContaId(idOrigem);

        Cliente clienteDestino =  clienteServico.filtrarClientePorContaId(idDestino);

        List<Cliente> lista = new ArrayList<>();
        TransferirDTO transferirDTO = new TransferirDTO(transferir);
        clienteOrigem.getConta().setSaldo(retornoSaldoSaque(clienteOrigem, new SacarDTO(transferir)));
        clienteDestino.getConta().setSaldo(retornoSaldoDeposito(clienteDestino, new DepositarDTO(transferir)));
        lista.add(clienteOrigem);
        lista.add(clienteDestino);

        return operacao(transferirDTO, lista, TipoTransacao.TRANSFERENCIA);
    }

    private <T extends OperacaoDTO> T operacao(T operacao, List<Cliente> cliente, TipoTransacao tipoTransacao){
        Transacao transacao = new Transacao();
        transacao.setTipoTransacao(tipoTransacao);
        transacao.setDescricao(operacao.getDescricao());
        transacao.setValor(operacao.getValor());
        transacao.setConta(cliente.get(0).getConta());
        if(cliente.size() == 2){
            transacao.setIdContaDestino(cliente.get(1).getConta().getContaId());
        }
        operacao.setSaldo(cliente.get(0).getConta().getSaldo());
        operacao.setTipoTransacao(tipoTransacao);
        if(operacao instanceof TransferirDTO){
            ((TransferirDTO) operacao).setIdContaOrigem(transacao.getConta().getContaId());
            ((TransferirDTO) operacao).setIdContaDestino(transacao.getIdContaDestino());
        }

        cliente.forEach(c -> c .getConta().getTransacoes().add(transacaoRepositorio.save(transacao)));
        cliente.forEach(clienteServico::salvarCliente);

        return operacao;
    }

    private Double retornoSaldoSaque(Cliente cliente, SacarDTO sacar){
        if(cliente.getConta().getSaldo() < sacar.getValor()){
            throw new SaldoInsuficienteException("O saldo de " + cliente.getNome() + " é insuficiente");
        }

        Double novoSaldo =  cliente.getConta().getSaldo() - sacar.getValor();
        cliente.getConta().setSaldo(novoSaldo);

        return novoSaldo;
    }

    public Double retornoSaldoDeposito(Cliente cliente, DepositarDTO deposito){
        Double saldo = deposito.getValor() + cliente.getConta().getSaldo();
         cliente.getConta().setSaldo(saldo);
         return cliente.getConta().getSaldo();
    }

}
