package br.com.panacademy.bluebank.modelo;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @CreationTimestamp
    private LocalDateTime dataTransacao;

    private Double valor;

    @ManyToOne(fetch = FetchType.LAZY) //Isso significa que ao realizarmos um “SELECT * from contaLazy” teremos todos os campos retornados, mas os campos com a propriedade FetchType.LAZY estarão nulos, mesmo que eles existam no banco. Essa é uma forma de não sobrecarregar sua aplicação com dados inúteis que não serão utilizados, tornando-a rápida e performática.
    private Conta contaOrigem;

    @ManyToOne(fetch = FetchType.LAZY)
    private Conta contaDestino;

    public Transacao() {
    }

    public Transacao (Long id, LocalDateTime dataTransacao, Double valor) {
        this.id = id;
        this.dataTransacao = dataTransacao;
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataTransacao() {
        return dataTransacao;
    }

    public void setDataTransacao(LocalDateTime dataTransacao) {
        this.dataTransacao = dataTransacao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Conta getContaOrigem() {
        return contaOrigem;
    }

    public void setContaOrigem(Conta contaOrigem) {
        this.contaOrigem = contaOrigem;
    }

    public Conta getContaDestino() {
        return contaDestino;
    }

    public void setContaDestino(Conta contaDestino) {
        this.contaDestino = contaDestino;
    }

}


