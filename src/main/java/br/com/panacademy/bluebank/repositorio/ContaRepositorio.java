package br.com.panacademy.bluebank.repositorio;

import br.com.panacademy.bluebank.modelo.Cliente;
import br.com.panacademy.bluebank.modelo.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepositorio extends JpaRepository<Conta, Long> {
}
