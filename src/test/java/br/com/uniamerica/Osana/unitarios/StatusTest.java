package br.com.uniamerica.Osana.unitarios;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

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

import br.com.uniamerica.Osana.Model.Status;
import br.com.uniamerica.Osana.Repository.StatusRepository;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class StatusTest {
	
	@Mock
	@Autowired
	private StatusRepository repository;
	
	@Test
	@DisplayName("Teste da requisição POST")
	public void saveStatus() throws Exception {
		Status Statuse = new Status(1L, "Ana Carolina");
		repository.save(Statuse);
		assertNotNull(Statuse);
	}
	
	@Test
	@DisplayName("Teste da requisição GET")
	public void findByStatus () throws Exception {
		Status Status1 = new Status
		(1L, "Ana Carolina");
		Status Status2 = new Status
		(1L, "Ana Carolina");
		List<Status> Statuses = new ArrayList<>();
		Statuses.add(Status1);
		Statuses.add(Status2);
	}
	
	@Test
	@DisplayName("Teste da requisição UPDATE")
	public void updateStatus () throws Exception {
		String name = "Ana Carolina";
		Status Membro = new Status(1L, "Ana Carolina");
		repository.save(Membro);
		Status Statuse = repository.findById(1L).get();
		Statuse.setName(name);
		Status liga = repository.save(Statuse);
		assertEquals(liga.getName(),name);
	}
	
	@Test
	@DisplayName("Teste da requisição DELETE")
	public void deleteStatus() throws Exception {
		Status Statuse = new Status(1L, "Ana Carolina");
		repository.save(Statuse);
		Long id = Statuse .getId();
		boolean cli1 = repository.findById(id).isPresent();
		repository.deleteById(id);
		boolean cli2 = repository.findById(id).isPresent();
		assertTrue(cli1);
		assertFalse(cli2);
	}

}
