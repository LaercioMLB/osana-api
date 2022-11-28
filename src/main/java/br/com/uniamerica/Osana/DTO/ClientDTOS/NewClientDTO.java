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
    @NotBlank(message = "First Name is Required")
    private String firstName;
    @NotBlank(message = "Last Name is Required")
    private String lastName;
    private String email;
    private String phone;
    private String contract;
    @NotBlank(message = "CNPJ is Required")
    private String cnpj;


    public Client toModel(){
        Client client = new Client();
        client.setFirstName(getFirstName());
        client.setLastName(getLastName());
        client.setEmail(getEmail());
        client.setPhone(getPhone());
        client.setContract(getContract());
        client.setCnpj(getCnpj());

        return client;
    }
    public Client updatedClient(Client updateClient, ClientRepository clientRepository){
        updateClient.setFirstName(this.firstName);
        updateClient.setLastName(this.lastName);
        updateClient.setEmail(this.email);
        updateClient.setPhone(this.phone);
        updateClient.setContract(this.contract);
        updateClient.setCnpj(this.cnpj);
        return updateClient;
    }
}