package br.com.panacademy.bluebank.controle;

import br.com.panacademy.bluebank.modelo.Cliente;
import br.com.panacademy.bluebank.repositorio.ClienteRepositorio;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteControle {

    private final ClienteRepositorio repository;

    public ClienteControle(ClienteRepositorio repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Cliente> findAll(){
        return repository.findAll();
    }
}
