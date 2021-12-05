package br.com.panacademy.bluebank.modelo;

import br.com.panacademy.bluebank.modelo.Utils.AbstractEntity;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;


@Entity
public class Perfil extends AbstractEntity<Long> implements GrantedAuthority {

    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String getAuthority() {
        return nome;
    }
}
