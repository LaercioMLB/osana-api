package br.com.uniamerica.Osana.DTO.UserDTOS;

import br.com.uniamerica.Osana.Model.Usuario;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UsuarioDTO implements Serializable {

    private Long id;
    private String name;
    private String username;
    private String email;
    private Collection<? extends GrantedAuthority> roles;

    private static List<UsuarioDTO> usuarioDTOS;

    public UsuarioDTO(Usuario user) {
        this.id = user.getId();
        this.name = user.getName();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.roles = user.getAuthorities();
    }

    public static List<UsuarioDTO> convert(List<Usuario> usersList) {
        usuarioDTOS = new ArrayList<>();
        usuarioDTOS.addAll(usersList.stream().map(UsuarioDTO::new).collect(Collectors.toList()));
        return usuarioDTOS;
    }

    public Collection<? extends GrantedAuthority> getRoles() {
        return roles;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}
