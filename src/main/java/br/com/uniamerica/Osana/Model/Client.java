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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "client")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client implements Serializable {
	private static final long serialVersionUID = -2706375515161192105L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "O nome não pode ser vazio")
	@Column(name = "name", length = 45)
	private String firstName;

	@NotEmpty(message = "O sobrenome não pode ser vazio")
	@Column(name = "sobrenome", length = 45)
	private String lastName;

	@NotEmpty(message = "O emil não pode ser vazio")
	@Column(name = "email", length = 45)
	private String email;

	@NotEmpty(message = "O telefone não pode ser vazio")
	@Column(name = "phone", length = 45)
	private String phone;
	
	@NotEmpty(message = "O contrato é necessario")
	@Column(name = "contract", length = 45)
	private String contract;
	
	@NotEmpty(message = "O CNPJ é necessario")
	@Column(name = "cnpj", length = 45)
	private String cnpj;
}
