package br.com.panacademy.bluebank.controle;

import br.com.panacademy.bluebank.model.Cliente;
import br.com.panacademy.bluebank.repositorio.ClienteRepositorio;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/clientes")

public class ClienteControle  {

    private final ClienteRepositorio repositorio;

    public ClienteControle(ClienteRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @GetMapping("/listar")
    public List<Cliente> findAll(){
        return repositorio.findAll();
    }

    @GetMapping("/{id}")
    public Cliente findById(@PathVariable Long id) {
        return repositorio.findById(id).orElse(null);
   }

}
