package br.com.panacademy.bluebank.service;


import br.com.panacademy.bluebank.model.Cliente;
import br.com.panacademy.bluebank.web.dto.ClienteRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface ClienteService extends UserDetailsService {
    Cliente save(ClienteRegistrationDto registrationDto);
}
