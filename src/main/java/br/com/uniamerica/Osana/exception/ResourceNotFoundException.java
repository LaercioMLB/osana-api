package br.com.uniamerica.Osana.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String msg) {
        super(msg);
    }

    public ResourceNotFoundException() {
        super("Recurso não Encontrado!");
    }
}