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

import br.com.uniamerica.Osana.Model.Priority;
import br.com.uniamerica.Osana.Repository.PriorityRepository;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class PriorityTest {
	
	@Mock
	@Autowired
	private PriorityRepository repository;
	
	@Test
	@DisplayName("Teste da requisição POST")
	public void savePriority() throws Exception {
		Priority Prioritye = new Priority(1L, "Alta");
		repository.save(Prioritye);
		assertNotNull(Prioritye);
	}
	
	@Test
	@DisplayName("Teste da requisição GET")
	public void findByPriority() throws Exception {
		Priority Prioritye1 = new Priority
		(1L, "Alta");
		Priority Prioritye2 = new Priority
		(1L, "Alta");
		List<Priority> Priorityes = new ArrayList<>();
		Priorityes.add(Prioritye1);
		Priorityes.add(Prioritye2);
	}
	
	@Test
	@DisplayName("Teste da requisição UPDATE")
	public void UpdatePriority()throws Exception  {
	   String Name = "Alta";
	   Priority priority = new Priority(1L, "Alta");
	   priority.setId(1);
	   repository.save(priority);
	   Priority updatedProduct = repository.findById(Name);
	   Assertions.assertEquals(updatedProduct.getName(), Name);
	}
	
	@Test
	@DisplayName("Teste da requisição DELETE")
	public void deletePriority() throws Exception {
		Priority Prioritye = new Priority(1L, "Alta");
		repository.save(Prioritye);
		Long id = Prioritye .getId();
		boolean pri1 = repository.findById(id).isPresent();
		repository.deleteById(id);
		boolean pri2 = repository.findById(id).isPresent();
		assertTrue(pri1);
		assertFalse(pri2);
	}

}
