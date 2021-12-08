package br.com.panacademy.bluebank.servico;

import br.com.panacademy.bluebank.dto.perfil.CadastrarPefilDTO;
import br.com.panacademy.bluebank.dto.perfil.DeletarPerfilDTO;
import br.com.panacademy.bluebank.excecao.RecursoNaoEncontradoException;
import br.com.panacademy.bluebank.modelo.Perfil;
import br.com.panacademy.bluebank.repositorio.PerfilRespositorio;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PerfilServico {

    private final PerfilRespositorio perfilRespositorio;

    public PerfilServico(PerfilRespositorio perfilRespositorio) {
        this.perfilRespositorio = perfilRespositorio;
    }

    public Perfil localizarPeloNome(String nome){
        return perfilRespositorio.findByNome(nome).orElseThrow(() -> new RecursoNaoEncontradoException("Pefil nao localizado " + nome));
    }

    public Boolean cadastrarPefil(CadastrarPefilDTO cadastrarPefil){
        String nomeRole = "ROLE_" + cadastrarPefil.getNome();
        Optional<Perfil> byNome = perfilRespositorio.findByNome(nomeRole);
        if(byNome.isPresent()) {
            throw new IllegalArgumentException("Perfil ja cadastrado em sistema: " + nomeRole);
        }
        perfilRespositorio.save(new Perfil(nomeRole));
        return true;
    }

    public Boolean deletarPerfil(DeletarPerfilDTO deletarPefil){
        String nomeRole = "ROLE_" + deletarPefil.getNome();
        Optional<Perfil> byNome = perfilRespositorio.findByNome(nomeRole);
        if(byNome.isPresent()) {
            perfilRespositorio.delete(byNome.get());
            return true;
        }
        throw new RecursoNaoEncontradoException("Nao foi identificado o perfil " + nomeRole + " nos registros");
    }

}