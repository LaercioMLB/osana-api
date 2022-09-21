package br.com.uniamerica.Osana.Model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

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

    public Status() {
    }

    public Status(Long id, String name) {
        this.id = id;
        this.name = name;
    }

	public Status(String string) {
		// TODO Auto-generated constructor stub
	}

	public Status statusCode(int value) {
		// TODO Auto-generated method stub
		return null;
	}


}
