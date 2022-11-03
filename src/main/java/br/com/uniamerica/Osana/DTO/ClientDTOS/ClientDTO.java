package br.com.uniamerica.Osana.DTO.ClientDTOS;

import java.util.Optional;

import br.com.uniamerica.Osana.Model.Client;

public class ClientDTO {
	
	private Long id;
	private String name;
	private String contract;
	private String cnpj;
	
	public ClientDTO() {		
	}

	public ClientDTO(Client entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.contract = entity.getContract();
		this.cnpj = entity.getCnpj();
	}

	public ClientDTO(Optional<Client> findById) {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContract() {
		return contract;
	}

	public void setContract(String contract) {
		this.contract = contract;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
}
