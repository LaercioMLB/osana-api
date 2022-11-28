package br.com.uniamerica.Osana.DTO.ClientDTOS;

import java.util.Optional;

import br.com.uniamerica.Osana.Model.Client;

public class ClientDTO {
	
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String contract;
	private String cnpj;
	
	public ClientDTO() {		
	}

	public ClientDTO(Client entity) {
		this.id = entity.getId();
		this.firstName = entity.getFirstName();
		this.lastName=entity.getLastName();
		this.email=entity.getEmail();
		this.phone=entity.getPhone();
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

	public String getFirstName() {
		return firstName;
	}

	public String getLastName(){return lastName;}

	public String getEmail(){return email;}

	public String getPhone(){return phone;}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName){this.lastName = lastName;}

	public void setEmail(String email){this.email = email;}

	public void setPhone(String phone){this.phone = phone;}

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
