package br.com.panacademy.bluebank.service;


import br.com.panacademy.bluebank.model.Cliente;
import br.com.panacademy.bluebank.model.Funcao;
import br.com.panacademy.bluebank.repository.ClienteRepository;
import br.com.panacademy.bluebank.web.dto.ClienteRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImpl implements ClienteService {

    private ClienteRepository clienteRepository;

    @Autowired
    private BCryptPasswordEncoder senhaEncoder;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        super();
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Cliente save(ClienteRegistrationDto registrationDto) {
        Cliente cliente = new Cliente(registrationDto.getNome(),
                registrationDto.getSobrenome(), registrationDto.getEmail(),
                senhaEncoder.encode(registrationDto.getSenha()), registrationDto.getCpf(), Arrays.asList(new Funcao("FUNCAO_CLIENTE")));
        return clienteRepository.save(cliente);
    }


    @Override
    public UserDetails loadUserByUsername(String clientecpf) throws UsernameNotFoundException {

        Cliente cliente = clienteRepository.findByCpf(clientecpf);
        if(cliente == null) {
            throw new UsernameNotFoundException("Senha ou usuario Invalido.");
        }
        return new org.springframework.security.core.userdetails.User(cliente.getCpf(), cliente.getSenha(), mapFuncoesToAuthorities(cliente.getFuncoes()));
    }

    private Collection<? extends GrantedAuthority> mapFuncoesToAuthorities(Collection<Funcao> funcoes){

        return funcoes.stream().map(funcao -> new SimpleGrantedAuthority(funcao.getName())).collect(Collectors.toList());
    }
}
