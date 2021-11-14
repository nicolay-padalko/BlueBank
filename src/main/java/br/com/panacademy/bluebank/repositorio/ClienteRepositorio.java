package br.com.panacademy.bluebank.repositorio;

import br.com.panacademy.bluebank.modelo.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {

}

