package br.com.panacademy.bluebank.controle;


import br.com.panacademy.bluebank.dto.perfil.CadastrarPefilDTO;
import br.com.panacademy.bluebank.dto.perfil.DeletarPerfilDTO;
import br.com.panacademy.bluebank.servico.PerfilServico;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("perfis")
public class PerfilControle {

    private final PerfilServico perfilServico;

    public PerfilControle(PerfilServico perfilServico) {
        this.perfilServico = perfilServico;
    }


    @PostMapping
    @ApiOperation("Cadastro de perfil do cliente")
    public ResponseEntity<CadastrarPefilDTO> adicionarPerfil (@RequestBody @Valid CadastrarPefilDTO cadastrarPefil){
        Boolean salvou = perfilServico.cadastrarPefil(cadastrarPefil);
        if(salvou){
            return ResponseEntity.ok().body(cadastrarPefil);
        }
        return ResponseEntity.badRequest().body(cadastrarPefil);
    }

    @DeleteMapping
<<<<<<< HEAD
    @ApiOperation("Exclusão de perfil do cliente")
    public ResponseEntity<DeletarPerfilDTO> adicionarPerfil (@RequestBody @Valid DeletarPerfilDTO deletarPefil){
=======
    public ResponseEntity<DeletarPerfilDTO> deletarPerfil (@RequestBody @Valid DeletarPerfilDTO deletarPefil){
>>>>>>> 9a9138dbca151b7ea0b75aa5cd3c64d9cd6c64b6
        Boolean deletou = perfilServico.deletarPerfil(deletarPefil);
        if(deletou){
            return ResponseEntity.ok().body(deletarPefil);
        }
        return ResponseEntity.badRequest().body(deletarPefil);
    }
}