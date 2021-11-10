package br.com.panacademy.bluebank.controller;

import br.com.panacademy.bluebank.model.Cliente;
import br.com.panacademy.bluebank.repository.ClienteRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteRepository repository;

    public ClienteController(ClienteRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Cliente> findAll(){
        return repository.findAll();
    }
}
