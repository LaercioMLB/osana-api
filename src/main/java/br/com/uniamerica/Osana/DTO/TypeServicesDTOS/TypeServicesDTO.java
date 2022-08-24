package br.com.uniamerica.Osana.DTO.TypeServicesDTOS;

import br.com.uniamerica.Osana.Model.TypeServices;

import java.io.Serializable;

public class TypeServicesDTO implements Serializable {
    private Long idTypeServices;
    private String services;


    public TypeServicesDTO(TypeServices typeServices){
        this.idTypeServices = typeServices.getIdTypeServices();
        this.services = typeServices.getServices();
    }
}
