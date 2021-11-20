package br.com.panacademy.bluebank.servico;

import br.com.panacademy.bluebank.modelo.Conta;
import br.com.panacademy.bluebank.modelo.Transacao;
import br.com.panacademy.bluebank.repositorio.ContaRepositorio;
import org.springframework.stereotype.Service;

@Service
public class ContaServico {

    Conta conta = new Conta();
    private ContaRepositorio contaRepositorio;


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

}
