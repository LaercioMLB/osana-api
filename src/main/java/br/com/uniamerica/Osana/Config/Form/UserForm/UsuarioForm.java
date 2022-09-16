package br.com.uniamerica.Osana.Config.Form.UserForm;

import br.com.uniamerica.Osana.Model.Role;
import br.com.uniamerica.Osana.Model.Usuario;
import br.com.uniamerica.Osana.Repository.RoleRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public class UsuarioForm {
    @NotNull
    @NotEmpty
    private String name;
    @NotNull
    @NotEmpty
    private String username;
    @NotNull
    @NotEmpty
    private String email;
    @NotNull
    @NotEmpty
    private String password;

    @NotNull
    @NotEmpty
    private String typeUser;

    private BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public Usuario convert(RoleRepository roleRepository){
        Optional<Role> role = roleRepository.findByName(this.typeUser);
        if (role.isEmpty()){
            return null;
        }
        return new Usuario(this.name, this.username, this.email, passwordEncoder().encode(this.password), role.get());
    }

    public UsuarioForm(String name, String username, String email, String password, String typeUser) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.typeUser = typeUser;
    }

    public String getTypeUser() {
        return typeUser;
    }

    public void setTypeUser(String typeUser) {
        this.typeUser = typeUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
