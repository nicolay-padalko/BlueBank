package br.com.panacademy.bluebank.servico;

import br.com.panacademy.bluebank.dto.usuario.cliente.AtualizarClienteDTO;
import br.com.panacademy.bluebank.dto.usuario.cliente.AtualizarCredenciaisClienteDTO;
import br.com.panacademy.bluebank.dto.usuario.cliente.CadastrarClienteDTO;
import br.com.panacademy.bluebank.dto.usuario.cliente.ClienteDTO;
import br.com.panacademy.bluebank.dto.usuario.funcionario.AtualizarCredenciaisFuncionarioDTO;
import br.com.panacademy.bluebank.dto.usuario.funcionario.AtualizarFuncionarioDTO;
import br.com.panacademy.bluebank.dto.usuario.funcionario.CadastrarFuncionarioDTO;
import br.com.panacademy.bluebank.dto.usuario.funcionario.FuncionarioDTO;
import br.com.panacademy.bluebank.excecao.RecursoNaoEncontradoException;
import br.com.panacademy.bluebank.modelo.Perfil;
import br.com.panacademy.bluebank.modelo.usuario.Cliente;
import br.com.panacademy.bluebank.modelo.Conta;
import br.com.panacademy.bluebank.modelo.usuario.Funcionario;
import br.com.panacademy.bluebank.repositorio.FuncionarioRepositorio;
import br.com.panacademy.bluebank.repositorio.PerfilRespositorio;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FuncionarioServico {

    private final FuncionarioRepositorio funcionarioRepositorio;
    private final PerfilRespositorio perfilRespositorio;

    public FuncionarioServico(FuncionarioRepositorio funcionarioRepositorio, PerfilRespositorio perfilRespositorio) {
        this.funcionarioRepositorio = funcionarioRepositorio;
        this.perfilRespositorio = perfilRespositorio;
    }


    @Transactional(readOnly = true)
    public List<FuncionarioDTO> listarTodos() {
        List<Funcionario> listaFuncionarios = funcionarioRepositorio.findAll();
        return listaFuncionarios.stream().map(FuncionarioDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public FuncionarioDTO filtrarPorId(Long id) {
        Funcionario funcionario = funcionarioRepositorio.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Funcionario não encontrado: "+id));
        return new FuncionarioDTO(funcionario);
    }

    @Transactional
    public FuncionarioDTO salvarFuncionario(Funcionario funcionario) {

        FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
        BeanUtils.copyProperties(funcionario, funcionarioDTO);

        funcionarioRepositorio.save(funcionario);
        return new FuncionarioDTO(funcionario);
    }

    @Transactional
    public FuncionarioDTO salvarFuncionario(CadastrarFuncionarioDTO funcionario) {

        String senhaEncriptografada = new BCryptPasswordEncoder().encode(funcionario.getSenha());
        funcionario.setSenha(senhaEncriptografada);

        FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
        BeanUtils.copyProperties(funcionario, funcionarioDTO);

        Funcionario funcionario1 = funcionario.toFuncionario();
        funcionario1.adicionarPefil(perfilRespositorio.findById(2L).get());
        funcionario1.adicionarPefil(perfilRespositorio.findById(1L).get());
        funcionario1.adicionarPefil(new Perfil("ROLE_CLIENTE"));
        funcionario1.adicionarPefil(new Perfil("ROLE_ADMIN"));
        funcionarioRepositorio.save(funcionario1);
        return new FuncionarioDTO(funcionario1);
    }

    public void deletarFuncionario(Long id) {
        try {
            funcionarioRepositorio.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new RecursoNaoEncontradoException("Funcionario não encontrado " + id);
        }
    }

    @Transactional
    public AtualizarCredenciaisFuncionarioDTO atualizarCredenciaisFuncionario(Long id, AtualizarCredenciaisFuncionarioDTO dto) {
        Funcionario entidade = funcionarioRepositorio.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Funcionario de id: " + id + " não encontrado"));

        BeanUtils.copyProperties(dto, entidade);

        entidade = funcionarioRepositorio.save(entidade);
        return new AtualizarCredenciaisFuncionarioDTO(entidade);
    }


    @Transactional
    public AtualizarFuncionarioDTO atualizarFuncionario(Long id, AtualizarFuncionarioDTO dto) {
        Funcionario entidade = funcionarioRepositorio.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Funcionario não encontrado: " + id));

        BeanUtils.copyProperties(dto, entidade);

        entidade = funcionarioRepositorio.save(entidade);
        return new AtualizarFuncionarioDTO(entidade);
    }
}