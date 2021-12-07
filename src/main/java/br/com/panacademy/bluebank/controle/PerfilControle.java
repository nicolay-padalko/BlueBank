package br.com.panacademy.bluebank.controle;


import br.com.panacademy.bluebank.dto.perfil.CadastrarPefilDTO;
import br.com.panacademy.bluebank.dto.perfil.DeletarPerfilDTO;
import br.com.panacademy.bluebank.servico.PerfilServico;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("perfil")
public class PerfilControle {

    private final PerfilServico perfilServico;

    public PerfilControle(PerfilServico perfilServico) {
        this.perfilServico = perfilServico;
    }

    @PostMapping
    public ResponseEntity<CadastrarPefilDTO> adicionarPerfil (@RequestBody @Valid CadastrarPefilDTO cadastrarPefil){
        Boolean salvou = perfilServico.cadastrarPefil(cadastrarPefil);
        if(salvou){
            return ResponseEntity.ok().body(cadastrarPefil);
        }
        return ResponseEntity.badRequest().body(cadastrarPefil);
    }

    @DeleteMapping
    public ResponseEntity<DeletarPerfilDTO> deletarPerfil (@RequestBody @Valid DeletarPerfilDTO deletarPefil){
        Boolean deletou = perfilServico.deletarPerfil(deletarPefil);
        if(deletou){
            return ResponseEntity.ok().body(deletarPefil);
        }
        return ResponseEntity.badRequest().body(deletarPefil);
    }
}