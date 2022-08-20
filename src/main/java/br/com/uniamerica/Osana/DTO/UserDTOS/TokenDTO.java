package br.com.uniamerica.Osana.DTO.UserDTOS;

public class TokenDTO {
    private String token;
    private String typeRequest;

    public TokenDTO(String token, String typeRequest) {
        this.token = token;
        this.typeRequest = typeRequest;
    }

    public String getToken() {
        return token;
    }

    public String getTypeRequest() {
        return typeRequest;
    }
}
