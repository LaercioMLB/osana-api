package br.com.uniamerica.Osana.DTO.ClientDTOS;

import br.com.uniamerica.Osana.Model.*;
import br.com.uniamerica.Osana.Repository.ClientRepository;
import br.com.uniamerica.Osana.Repository.EquipmentRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class NewClientDTO {
    @NotBlank(message = "Name is Required")
    private String name;
    private String contract;
    @NotBlank(message = "CNPJ is Required")
    private String cnpj;


    public Client toModel(){
        Client client = new Client();
        client.setName(getName());
        client.setContract(getContract());
        client.setCnpj(getCnpj());

        return client;
    }
    public Client updatedClient(Client updateClient, ClientRepository clientRepository){
        updateClient.setName(this.name);
        updateClient.setContract(this.contract);
        updateClient.setCnpj(this.cnpj);
        return updateClient;
    }
}