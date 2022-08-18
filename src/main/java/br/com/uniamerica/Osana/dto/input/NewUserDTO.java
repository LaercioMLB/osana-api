package br.com.uniamerica.Osana.dto.input;

import br.com.uniamerica.Osana.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;

@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class NewUserDTO {
    @NotBlank(message = "name is required")
    private String name;
    @NotBlank(message = "username is required")
    private String username;
    @NotBlank(message = "password is required")
    private String password;
    @NotBlank(message = "contact is required")
    private String contact;
    public User toModel(){
        User user = new User();
        user.setName(getName());
        user.setUsername(getUsername());
        user.setPassword(getPassword());
        user.setContact(getContact());
        return user;
    }
}
