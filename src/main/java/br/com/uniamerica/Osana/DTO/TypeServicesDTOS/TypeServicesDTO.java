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

    public Long getIdTypeServices() {
        return idTypeServices;
    }

    public void setIdTypeServices(Long idTypeServices) {
        this.idTypeServices = idTypeServices;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }
}
