package br.com.panacademy.bluebank;

import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.transaction.Transaction;
import java.util.Calendar;
import java.util.List;

@Entity
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "conta_id")
    private Integer idCliente;

    @OneToOne(mappedBy = "conta")
    private Integer idCliente; //se o idCliente não for usado na classe cliente para identificar, alterar.

    @NotNull
    private Integer numeroConta;

    @Temporal(TemporalType.DATE)
    private Calendar dataCriacao;

    @NotNull
    private double saldo;

    @OneToOne
    private Cliente titular; //vai funcionar quando juntar a classe cliente

    @OneToMany
    private List<Transaction> transacaoList;



    public Conta(Integer idCliente,  @NotNull Integer numeroConta,
                 @NotNull double saldo) {
        super();
        this.idCliente = idCliente;
        this.numeroConta = numeroConta;
        System.out.println("Estou criando uma nova conta com número: " + this.numeroConta);
        this.saldo = saldo;
    }

    //para depositar informa valor e soma ao total da conta.
    public void deposita(double valor) {
        this.saldo = this.saldo + valor;
    }

    //para sacar o valor tem que ser maior ou igual ao valor presente na conta, caso contrario a transação retorna false;
    public boolean saca(double valor) {
        if(this.saldo >= valor) {
            this.saldo -= valor;
            return true;
        } else {
            return false;
        }
    }

    //para transferir informa o valor e o numero da conta destino
    public boolean transfere(double valor, Conta destino) {
        if(this.saca(valor)) {
            destino.deposita(valor);
            return true;
        } else {
            return false;
        }
    }

    public double getSaldo(){
        return this.saldo;
    }

    public List<Transaction> getTransacaoList() {
        return transacaoList;
    }

    public void setTransacaoList(List<Transaction> transacaoList) {
        this.transacaoList = transacaoList;
    }

    //para criar a conta não aceitamos numeros menor ou igual a zero no numero da conta.
    public void setNumero(int numero){
        if(numero <= 0) {
            System.out.println("Nao pode valor menor igual a 0");
            return;
        }
        this.numeroConta = numero;
    }


}
