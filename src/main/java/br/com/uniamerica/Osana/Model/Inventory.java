package br.com.uniamerica.Osana.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.Map;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inventory {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<OS> os;
    
	public Inventory(long l, String string) {
		// TODO Auto-generated constructor stub
	}

	public Inventory() {
		// TODO Auto-generated constructor stub
	}

	public Inventory(String string) {
		// TODO Auto-generated constructor stub
	}

	public void setName(String name2) {
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

	public void setId(long l, String string) {
		// TODO Auto-generated method stub
		
	}

	public Inventory statusCode(int value) {
		// TODO Auto-generated method stub
		return null;
	}
}
