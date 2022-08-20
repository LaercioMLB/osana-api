package br.com.uniamerica.Osana.Config.Form.UserForm;

import br.com.uniamerica.Osana.Model.Role;
import br.com.uniamerica.Osana.Model.Usuario;
import br.com.uniamerica.Osana.Repository.RoleRepository;
import br.com.uniamerica.Osana.Repository.UsuarioRepository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UpdateUsuarioForm {

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
    private String typeUser;

    public Usuario updateUser(Usuario user, RoleRepository roleRepository, UsuarioRepository usuarioRepository){
        Optional<Role> role = roleRepository.findByName(this.typeUser);
        user.setName(this.name);
        user.setEmail(this.email);
        user.setUsername(this.username);
        if (role.isPresent()){
            List<Role> listRoles = new ArrayList<>();
            listRoles.add(role.get());
            user.setRoles(listRoles);
        }
        return user;
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

    public String getTypeUser() {
        return typeUser;
    }

    public void setTypeUser(String typeUser) {
        this.typeUser = typeUser;
    }
}
