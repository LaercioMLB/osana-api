package br.com.uniamerica.Osana.unitarios;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.uniamerica.Osana.Model.Role;
import br.com.uniamerica.Osana.Repository.RoleRepository;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class RoleTest {
	
	@Mock
	@Autowired
	private RoleRepository repository;
	
	@Test
	@DisplayName("Teste da requisição POST")
	public void saveRole() throws Exception {
		Role Rolee = new Role(1L, "Alta");
		repository.save(Rolee);
		assertNotNull(Rolee);
	}
	
	@Test
	@DisplayName("Teste da requisição GET")
	public void findByRole() throws Exception {
		Role Rolee1 = new Role
		(1L, "tal");
		Role Rolee2 = new Role
		(1L, "tal");
		List<Role> Rolees = new ArrayList<>();
		Rolees.add(Rolee1);
		Rolees.add(Rolee2);
	}
	
	@Test
	@DisplayName("Teste da requisição UPDATE")
	public void UpdateRole()throws Exception  {
	   String Name = "Alta";
	   Role Role = new Role(1L, "tal");
	   Role.setId(1L, "tal");
	   repository.save(Role);
	   Role updatedProduct = repository.findById(Name);
	   Assertions.assertEquals(updatedProduct.getName(), Name);
	}
	
	@Test
	@DisplayName("Teste da requisição DELETE")
	public void deleteRole() throws Exception {
		Role inven = new Role(1L, "tal");
		repository.save(inven);
		Long id = inven.getId();
		boolean pri1 = repository.findById(id).isPresent();
		repository.deleteById(id);
		boolean pri2 = repository.findById(id).isPresent();
		assertTrue(pri1);
		assertFalse(pri2);
	}

}
