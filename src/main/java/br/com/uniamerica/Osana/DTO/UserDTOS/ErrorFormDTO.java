package br.com.uniamerica.Osana.DTO.UserDTOS;

public class ErrorFormDTO {
    private String field;
    private String error;

    public ErrorFormDTO(String field, String error) {
        this.field = field;
        this.error = error;
    }

    public String getField() {
        return field;
    }

    public String getError() {
        return error;
    }
}
