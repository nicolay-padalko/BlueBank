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

        Optional<Perfil> byNome = perfilRespositorio.findByNome(String.valueOf(cadastrarPefil.getNome()));
        if(byNome.isPresent()) {
            throw new RecursoNaoEncontradoException("Perfil ja cadastrado em sistema: " + byNome.get().getNome());
        }
        perfilRespositorio.save(new Perfil(cadastrarPefil.getNome()));
        return true;
    }

    public Boolean deletarPerfil(DeletarPerfilDTO deletarPefil){

        Optional<Perfil> byNome = perfilRespositorio.findByNome(String.valueOf(deletarPefil.getNome()));
        if(byNome.isPresent()) {
            perfilRespositorio.delete(byNome.get());
            return true;
        }
        throw new RecursoNaoEncontradoException("Nao foi identificado o perfil " + deletarPefil.getNome() + " nos registros");
    }

}
