package br.com.panacademy.bluebank.repositorio;

import br.com.panacademy.bluebank.modelo.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransacaoRepositorio extends JpaRepository<Transacao, Long> {

    @Query("SELECT t FROM Transacao t WHERE t.conta.contaId = :idContaUsuario")
    List<Transacao> findAllByContaUsuario(Long idContaUsuario);

}
