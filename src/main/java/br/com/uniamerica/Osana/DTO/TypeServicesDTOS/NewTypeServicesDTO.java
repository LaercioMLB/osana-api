package br.com.uniamerica.Osana.DTO.TypeServicesDTOS;

import br.com.uniamerica.Osana.Model.TypeServices;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public class NewTypeServicesDTO{
   // @NotBlank(message = "Type Services is required")
    @NotNull
    @NotEmpty
    private String services;

    public TypeServices toModel(){
        TypeServices typeServices = new TypeServices();
        typeServices.setServices(this.services);
        return typeServices;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public NewTypeServicesDTO(String services) {
        this.services = services;
    }

    public NewTypeServicesDTO() {

    }
}
