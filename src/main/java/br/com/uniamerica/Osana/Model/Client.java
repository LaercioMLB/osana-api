package br.com.uniamerica.Osana.Model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import br.com.uniamerica.Osana.Repository.ClientRepository;

@Entity
@Table(name = "client")
public class Client implements Serializable {
	private static final long serialVersionUID = -2706375515161192105L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "O nome não pode ser vazio")
	@Column(name = "name", length = 45)
	private String name;
	
	@NotEmpty(message = "O contrato é necessario")
	@Column(name = "contract", length = 45)
	private String contract;
	
	@NotEmpty(message = "O CNPJ é necessario")
	@Column(name = "cnpj", length = 45)
	private String cnpj;
	
	public Client(long l, String string, Object object) {		
	}

	public Client(Long id, String name, String contract, String cnpj) {
		this.id = id;
		this.name = name;
		this.contract = contract;
		this.cnpj = cnpj;
	}

	public Client(String string) {
		// TODO Auto-generated constructor stub
	}

	public Client(long l, String string, String string2, String string3, Object object) {
		// TODO Auto-generated constructor stub
	}

	public Client() {
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

	public Client statusCode(int value) {
		// TODO Auto-generated method stub
		return null;
	}

	public Client update(Client client, ClientRepository repository) {
		// TODO Auto-generated method stub
		return null;
	}
}
