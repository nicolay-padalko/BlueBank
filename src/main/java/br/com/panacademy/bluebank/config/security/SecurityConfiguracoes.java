package br.com.panacademy.bluebank.config.security;

import br.com.panacademy.bluebank.repositorio.ClienteRepositorio;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfiguracoes extends WebSecurityConfigurerAdapter {

    private final AutenticacaoService autenticacaoService;
    private final TokenServico tokenServico;
    private final ClienteRepositorio clienteRepositorio;

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    public SecurityConfiguracoes(AutenticacaoService autenticacaoService, TokenServico tokenServico, ClienteRepositorio clienteRepositorio) {
        this.autenticacaoService = autenticacaoService;
        this.tokenServico = tokenServico;
        this.clienteRepositorio = clienteRepositorio;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET,"/clientes").hasRole("CLIENTE")
                .antMatchers(HttpMethod.GET,"/clientes/*").hasRole("CLIENTE")
                .antMatchers(HttpMethod.POST, "/auth").permitAll()
                .antMatchers("/perfil").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(new AutenticacaoViaTokenFiltro(tokenServico, clienteRepositorio), UsernamePasswordAuthenticationFilter.class);
    }

}
