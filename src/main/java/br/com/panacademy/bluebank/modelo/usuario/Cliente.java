package br.com.panacademy.bluebank.modelo.usuario;

import br.com.panacademy.bluebank.modelo.Conta;

import javax.persistence.*;

@Entity
@DiscriminatorValue("C")
public class Cliente extends Usuario{

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "conta_id", referencedColumnName = "conta_id")
    private Conta conta;

    public Cliente() {
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

}
