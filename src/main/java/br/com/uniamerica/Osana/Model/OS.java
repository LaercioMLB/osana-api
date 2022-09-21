package br.com.uniamerica.Osana.Model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import br.com.uniamerica.Osana.DTO.OSDTOS.NewOSDTO;

import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OS {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOS;

    private String motive;
    private String obs;
    private Date devolution;
    private Date dateOS;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Usuario usuario;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Status status;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Priority priority;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private TypeServices typeServices;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Client client;

    @ManyToMany(fetch = FetchType.EAGER)
    Set<Equipment> equipment;


    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Inventory> inventory;


	public OS(long l, String string, String string2, String string3, String string4) {
		// TODO Auto-generated constructor stub
	}


	public OS() {
		// TODO Auto-generated constructor stub
	}


	public OS(String string) {
		// TODO Auto-generated constructor stub
	}


	public OS(long l, String string, String string2, String string3) {
		// TODO Auto-generated constructor stub
	}


	public Long getIdOS() {
		// TODO Auto-generated method stub
		return null;
	}


	public void setMotive(String motive2) {
		// TODO Auto-generated method stub
		
	}


	public void setObs(String obs2) {
		// TODO Auto-generated method stub
		
	}


	public void setDevolution(Object devolution2) {
		// TODO Auto-generated method stub
		
	}


	public void setDateOS(Object dateOS2) {
		// TODO Auto-generated method stub
		
	}


	public void setStatus(Object status2) {
		// TODO Auto-generated method stub
		
	}


	public void setPriority(Object priority2) {
		// TODO Auto-generated method stub
		
	}


	public void setTypeServices(Object typeServices2) {
		// TODO Auto-generated method stub
		
	}


	public void setClient(@NotBlank(message = "Client is Required") Client client2) {
		// TODO Auto-generated method stub
		
	}


	public void setClient(Class<? extends NewOSDTO> class1) {
		// TODO Auto-generated method stub
		
	}


	public void setObs(Class<? extends NewOSDTO> class1) {
		// TODO Auto-generated method stub
		
	}


	public String getMotive() {
		// TODO Auto-generated method stub
		return null;
	}


	public String getObs() {
		// TODO Auto-generated method stub
		return null;
	}


	public Date getDevolution() {
		// TODO Auto-generated method stub
		return null;
	}


	public Date getDateOS() {
		// TODO Auto-generated method stub
		return null;
	}


	public Usuario getUsuario() {
		// TODO Auto-generated method stub
		return null;
	}


	public Status getStatus() {
		// TODO Auto-generated method stub
		return null;
	}


	public Priority getPriority() {
		// TODO Auto-generated method stub
		return null;
	}


	public TypeServices getTypeServices() {
		// TODO Auto-generated method stub
		return null;
	}


	public Client getClient() {
		// TODO Auto-generated method stub
		return null;
	}


	public OS statusCode(int value) {
		return null;
		// TODO Auto-generated method stub
		
	}

}
