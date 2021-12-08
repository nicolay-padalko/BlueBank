package br.com.panacademy.bluebank.repositorio;

import br.com.panacademy.bluebank.modelo.usuario.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface FuncionarioRepositorio extends JpaRepository<Funcionario, Long> {

    Optional<Funcionario> findByEmail(String email);

}