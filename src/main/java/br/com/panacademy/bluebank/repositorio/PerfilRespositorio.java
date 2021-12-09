package br.com.panacademy.bluebank.repositorio;

import br.com.panacademy.bluebank.modelo.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PerfilRespositorio extends JpaRepository<Perfil, Long> {

    Optional<Perfil> findByNome(String nome);

}
