package br.com.panacademy.bluebank.repositorio;

import br.com.panacademy.bluebank.modelo.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaRepositorio extends JpaRepository<Conta, Long> {
}
