package br.com.uniamerica.Osana.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Priority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPriority;

    private String name;


    public Priority(String string) {
    }


	public Priority(long l, String string) {
		// TODO Auto-generated constructor stub
	}


	public Priority() {
		// TODO Auto-generated constructor stub
	}


	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}


	public void setName(String name2) {
		// TODO Auto-generated method stub
		
	}


	public Object getName() {
		// TODO Auto-generated method stub
		return null;
	}


	public static Object builder() {
		// TODO Auto-generated method stub
		return null;
	}


	public void setId(int i) {
		// TODO Auto-generated method stub
		
	}


	public Priority statusCode(int value) {
		// TODO Auto-generated method stub
		return null;
	}
}
