package br.com.panacademy.bluebank.controle;

import br.com.panacademy.bluebank.DTOs.TransacaoDto;
import br.com.panacademy.bluebank.servico.TransacaoServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transacao")
public class TransacaoControle {

    @Autowired
    private TransacaoServico transacaoServico;



}
