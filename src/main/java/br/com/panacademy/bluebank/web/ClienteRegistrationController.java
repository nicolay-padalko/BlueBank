package br.com.panacademy.bluebank.web;

import br.com.panacademy.bluebank.service.ClienteService;
import br.com.panacademy.bluebank.web.dto.ClienteRegistrationDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registro")
public class ClienteRegistrationController {

    private ClienteService clienteService;

    public ClienteRegistrationController(ClienteService clienteService) {
        super();
        this.clienteService = clienteService;
    }

    @ModelAttribute("cliente")
    public ClienteRegistrationDto clienteRegistrationDto() {
        return new ClienteRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "registro";
    }

    @PostMapping
    public String registrationClienteConta(@ModelAttribute("cliente") ClienteRegistrationDto registrationDto) {
        clienteService.save(registrationDto);
        return "redirect:/registro?success";

    }
}
