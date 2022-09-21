package br.com.uniamerica.Osana.Model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class TypeServices {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTypeServices;
    private String services;



    //Getters e Setters
    public Long getIdTypeServices() {
        return idTypeServices;
    }

    public void setIdTypeServices(Long idTypeServices) {
        this.idTypeServices = idTypeServices;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    //Equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeServices that = (TypeServices) o;
        return idTypeServices.equals(that.idTypeServices);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTypeServices);
    }

    //Construtor
    public TypeServices(Long idTypeServices, String services) {
        this.idTypeServices = idTypeServices;
        this.services = services;
    }

    public TypeServices(String services) {
        this.services = services;
    }

    public TypeServices() {

    }

	public TypeServices statusCode(int value) {
		// TODO Auto-generated method stub
		return null;
	}

}
