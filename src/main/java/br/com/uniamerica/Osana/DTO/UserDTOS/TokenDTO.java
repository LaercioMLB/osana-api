package br.com.uniamerica.Osana.DTO.UserDTOS;

import java.io.Serializable;

public class TokenDTO {
    private String token;
    private String typeRequest;
    private UsuarioDTO user;

    public TokenDTO(String token, String typeRequest, UsuarioDTO user) {
        this.token = token;
        this.typeRequest = typeRequest;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public String getTypeRequest() {
        return typeRequest;
    }

    public UsuarioDTO getUser() {
        return user;
    }
}
