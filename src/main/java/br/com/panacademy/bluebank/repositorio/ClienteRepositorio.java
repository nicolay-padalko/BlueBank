package br.com.panacademy.bluebank.repositorio;

import br.com.panacademy.bluebank.modelo.usuario.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {

    @Query("SELECT c FROM Cliente c WHERE c.conta.contaId = :contaId")
    Optional<Cliente> findByClienteByContaId(Long contaId);

    @Override
    Page<Cliente> findAll(Pageable pageable);
}


