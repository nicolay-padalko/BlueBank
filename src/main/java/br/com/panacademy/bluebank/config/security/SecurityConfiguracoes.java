package br.com.panacademy.bluebank.config.security;

import br.com.panacademy.bluebank.repositorio.UsuarioRepositorio;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
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
    private final UsuarioRepositorio usuarioRepositorio;

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    public SecurityConfiguracoes(AutenticacaoService autenticacaoService, TokenServico tokenServico, UsuarioRepositorio usuarioRepositorio) {
        this.autenticacaoService = autenticacaoService;
        this.tokenServico = tokenServico;
        this.usuarioRepositorio = usuarioRepositorio;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/v2/api-docs",
                "/configuration/**",
                "/swagger-resources/**",
                "/swagger-ui.html",
                "/webjars/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/auth").permitAll()
                .antMatchers(HttpMethod.GET, "/clientes").hasRole("CLIENTE")
                .antMatchers(HttpMethod.GET, "/clientes/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/clientes").permitAll()
                .antMatchers(HttpMethod.DELETE, "/clientes/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/clientes").hasRole("CLIENTE")
                .antMatchers(HttpMethod.PUT, "/clientes/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/clientes/credenciais/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/contas").hasRole("CLIENTE")
                .antMatchers(HttpMethod.GET, "/contas/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/funcionarios").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/funcionarios/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/funcionarios").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/funcionarios/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/funcionarios/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/funcionarios/credenciais/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/funcionarios/cadastrarEmail/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/perfis").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/perfis").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/transacoes").hasRole("CLIENTE")
                .antMatchers(HttpMethod.POST, "/transacoes/depositar").hasRole("CLIENTE")
                .antMatchers(HttpMethod.POST, "/transacoes/sacar").hasRole("CLIENTE")
                .antMatchers(HttpMethod.POST, "/transacoes/transferir/*").hasRole("CLIENTE")
                .antMatchers(HttpMethod.POST, "/transacoes/depositar/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/transacoes/sacar/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/transacoes/transferir/*/*").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(new AutenticacaoViaTokenFiltro(tokenServico, usuarioRepositorio), UsernamePasswordAuthenticationFilter.class);
    }

}
