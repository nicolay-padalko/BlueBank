package br.com.panacademy.bluebank.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;

@Entity
@Table(name = "tb_conta")
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private Integer numeroConta;

    @NotNull
    private BigDecimal saldo;


    private Instant dataCriacao;

//
//    @OneToMany
//    private List<Transacao> transacaoList;

//    @OneToOne(mappedBy = "conta")
//    private Long idCliente; //se o idCliente não for usado na classe cliente para identificar, alterar.

   @PrePersist
   public void prePersist(){
       dataCriacao = Instant.now();
   }

    public Conta() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(Integer numeroConta) {
        this.numeroConta = numeroConta;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public Instant getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Instant dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
