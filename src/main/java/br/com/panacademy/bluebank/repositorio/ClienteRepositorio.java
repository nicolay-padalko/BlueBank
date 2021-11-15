package br.com.panacademy.bluebank.repositorio;

import br.com.panacademy.bluebank.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {

}

