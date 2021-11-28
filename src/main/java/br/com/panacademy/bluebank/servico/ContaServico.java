package br.com.panacademy.bluebank.servico;

import br.com.panacademy.bluebank.dto.ContaDTO;
import br.com.panacademy.bluebank.excecao.RecursoNaoEncontradoException;
import br.com.panacademy.bluebank.modelo.Conta;
import br.com.panacademy.bluebank.repositorio.ContaRepositorio;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContaServico {

    private final ContaRepositorio contaRepositorio;

    public ContaServico(ContaRepositorio contaRepositorio) {
        this.contaRepositorio = contaRepositorio;
    }

    @Transactional(readOnly = true)
    public List<ContaDTO> listarTodas() {
        List<Conta> listaContas = contaRepositorio.findAll();
        return listaContas.stream().map(ContaDTO::new).collect(Collectors.toList());
    }

    public void deletarConta(Long id) {
        try {
            contaRepositorio.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new RecursoNaoEncontradoException("Recurso não encontrado " + id);
        }
    }

    public ContaDTO filtrarContaPorId(Long id) {
        Conta conta = contaRepositorio.findById(id).
                orElseThrow(() -> new RecursoNaoEncontradoException("Conta não encontrado: "+id));
        return new ContaDTO(conta);
    }
}





//    Conta conta = new Conta();
//    private ContaRepositorio contaRepositorio;
//
//
//
//
//    //para sacar o valor tem que ser menor ou igual ao valor presente na conta, caso contrario a transação retorna false;
//    public boolean saca(double valor) {
//        var saldo = conta.getSaldo();
//        if(saldo >= valor) {
//            saldo -= valor;
//            return true;
//        } else {
//            return false;
//        }
//    }

//    //para depositar informa valor e soma ao total da conta.
//    public void deposita(double valor) {
//        this.valor = this.valor + valor;
//    }
//
//    //para transferir informa o valor e o numero da conta destino
//    public boolean transfere(double valor, Conta destino) {
//        if(this.saca(valor)) {
//            deposita(valor);
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//
//    //para criar a conta não aceitamos numeros menor ou igual a zero no numero da conta.
//    public void setNumeroConta(int numero){
//        if(numero <= 0) {
//            System.out.println("Nao pode valor menor igual a 0");
//            return;
//        }
//        this.numeroConta = numero;
//    }


