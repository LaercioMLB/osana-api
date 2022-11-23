package br.com.uniamerica.Osana.DTO.TypeServicesDTOS;

import br.com.uniamerica.Osana.Model.TypeServices;
import br.com.uniamerica.Osana.Repository.TypeServicesRepository;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public class NewTypeServicesDTO{
//    @NotNull(message = "Type Services is required")
    @NotNull
    @NotEmpty
    private String services;

    public TypeServices toModel(){
        TypeServices typeServices = new TypeServices();
        typeServices.setServices(this.services);
        return typeServices;
    }

    public TypeServices updateServices(TypeServices updateTypeServices, TypeServicesRepository typeServicesRepository){
        updateTypeServices.setServices(this.services);
        return updateTypeServices;
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
