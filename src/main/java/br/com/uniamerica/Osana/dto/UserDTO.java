package br.com.uniamerica.Osana.dto;

import br.com.uniamerica.Osana.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements Serializable {
    private Long idUser;
    private String name;
    private String username;

    private String contact;

    public UserDTO(User user){
        idUser = user.getId();
        name = user.getName();
        username = user.getUsername();
        contact = user.getContact();

    }
}
