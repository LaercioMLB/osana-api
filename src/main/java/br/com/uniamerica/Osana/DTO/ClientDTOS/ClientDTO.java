package br.com.uniamerica.Osana.DTO.ClientDTOS;

import br.com.uniamerica.Osana.Model.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO implements Serializable {
	private Long id;
	private String name;
	private String contract;
	private String cnpj;



	public ClientDTO(Client client){
		id = client.getId();
		name = client.getName();
		contract = client.getContract();
		cnpj = client.getCnpj();

	}
}
