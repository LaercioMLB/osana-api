package br.com.uniamerica.Osana.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

import java.util.Set;

@Builder
@Entity @Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("unused")
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String model;

    @ManyToMany(fetch = FetchType.EAGER)
    Set<OS> os;

	public void setName(String name2) {
		// TODO Auto-generated method stub
		
	}

	public void setModel(String model2) {
		// TODO Auto-generated method stub
		
	}

	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getModel() {
		// TODO Auto-generated method stub
		return null;
	}
}
