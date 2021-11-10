package br.com.panacademy.bluebank.repository;

import br.com.panacademy.bluebank.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}

