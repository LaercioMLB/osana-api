package br.com.uniamerica.Osana.Config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import br.com.uniamerica.Osana.Repository.UsuarioRepository;

@EnableWebSecurity
@EnableMethodSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfiguration {

    @Autowired
    private AutenticacaoService autenticacaoService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Configuração de Autorização(URL, perfil de acesso, etc..)
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers(HttpMethod.POST, "/auth").permitAll()
                //.antMatchers(HttpMethod.GET, "/users").hasRole("ROLE_TECNICO")
                .anyRequest().authenticated()
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(new AuthenticacaoTokenFilter(tokenService, usuarioRepository), UsernamePasswordAuthenticationFilter.class);

        http.headers().frameOptions().disable();
    }

    // Configuração de Autenticação (Login, controle de acesso, etc..)
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
    }

    public void configure(WebSecurity web) throws Exception{
        web.ignoring().antMatchers("/**.html", "/v2/api-docs", "/webjars/**","/configuration/**", "/swagger-resources/**");
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
        source.registerCorsConfiguration("/**", corsConfiguration);

        return source;
    }
}