package br.com.panacademy.bluebank.repositorio;

import br.com.panacademy.bluebank.modelo.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContaRepositorio extends JpaRepository<Conta, Long> {

    @Query("SELECT c.conta FROM Cliente c WHERE c.id = :id")
    Optional<Conta> findByIdUsuario(Long id);

}
