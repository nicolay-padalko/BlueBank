package br.com.panacademy.bluebank.modelo;

import com.sun.istack.NotNull;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_conta")
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "gerador")
    @SequenceGenerator(name="gerador", sequenceName="db_conta_gerador", initialValue = 50000, allocationSize = 10)
    @Column(name = "conta_id")
    private Long contaId;

    @NotNull
    private Double saldo;

    @NotNull
    @Column(name="criado_em")
    @CreationTimestamp
    private LocalDateTime criadoEm;

    @OneToMany(mappedBy = "conta")
    private List<Transacao> transacoes = new ArrayList<>();

    public Conta() {
    }

    public Long getContaId() {
        return contaId;
    }

    public void setContaId(Long contaId) {
        this.contaId = contaId;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(LocalDateTime dataCriacao) {
        this.criadoEm = dataCriacao;
    }

    public List<Transacao> getTransacoes() {
        return transacoes;
    }
}
