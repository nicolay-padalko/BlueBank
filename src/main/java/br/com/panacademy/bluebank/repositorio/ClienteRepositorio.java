package br.com.panacademy.bluebank.repositorio;

import br.com.panacademy.bluebank.modelo.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {

    @Query("SELECT c FROM Cliente c WHERE c.conta.contaId = :contaId")
    Optional<Cliente> findByClienteByContaId(Long contaId);

    Optional<Cliente> findByEmail(String email);
}


