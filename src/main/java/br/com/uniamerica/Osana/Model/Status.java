package br.com.uniamerica.Osana.Model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Status implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idStatus;

    private String name;

    public Long getId() {
        return idStatus;
    }

    public void setId(Long idStatus) {
        this.idStatus = idStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status() {
    }

    public Status(Long idStatus, String name) {
        this.idStatus = idStatus;
        this.name = name;
    }


}
