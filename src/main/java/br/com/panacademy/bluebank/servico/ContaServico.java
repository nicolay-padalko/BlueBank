package br.com.panacademy.bluebank.servico;

import br.com.panacademy.bluebank.dto.ContaDTO;
import br.com.panacademy.bluebank.excecao.RecursoNaoEncontradoException;
import br.com.panacademy.bluebank.modelo.Conta;
import br.com.panacademy.bluebank.repositorio.ContaRepositorio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContaServico {

    private final ContaRepositorio contaRepositorio;

    public ContaServico(ContaRepositorio contaRepositorio) {
        this.contaRepositorio = contaRepositorio;
    }

    @Transactional(readOnly = true)
    public List<ContaDTO> listarTodas() {
        List<Conta> listaContas = contaRepositorio.findAll();
        return listaContas.stream().map(ContaDTO::new).collect(Collectors.toList());
    }


    public ContaDTO filtrarContaPorId(Long id) {
        Conta conta = contaRepositorio.findById(id).
                orElseThrow(() -> new RecursoNaoEncontradoException("Conta não encontrado: "+id));
        return new ContaDTO(conta);
    }

    public Conta filtrarContaPorIdUsuario(Long id) {
        return contaRepositorio.findByIdUsuario(id).
                orElseThrow(() -> new RecursoNaoEncontradoException("Conta não encontrada"));
    }

}
