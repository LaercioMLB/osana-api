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

import br.com.uniamerica.Osana.Model.Client;
import br.com.uniamerica.Osana.Repository.ClientRepository;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ClientTest {
	
	@Mock
	@Autowired
	private ClientRepository repository;
	
	@Test
	@DisplayName("Teste da requisição POST")
	public void saveClient() throws Exception {
		Client Cliente = new Client(1L, "Ana Carolina", "true", "12345678");
		repository.save(Cliente);
		assertNotNull(Cliente);
	}
	
	@Test
	@DisplayName("Teste da requisição GET")
	public void findByClient() throws Exception {
		Client Cliente1 = new Client
		(1L, "Ana Carolina", "true", "12345678");
		Client Cliente2 = new Client
		(1L, "Bruno Douglas", "true", "12345678");
		List<Client> Clientes = new ArrayList<>();
		Clientes.add(Cliente1);
		Clientes.add(Cliente2);
	}
	
	@Test
	@DisplayName("Teste da requisição UPDATE")
	public void updateClient() throws Exception {
		String name = "Ana Carolina";
		Client Membro = new Client(1L, "Ana Carolina", "true", "12345678");
		repository.save(Membro);
		Client cliente = repository.findById(1L).get();
		cliente.setName(name);
		Client liga = repository.save(cliente);
		assertEquals(liga.getName(),name);
	}
	
	@Test
	@DisplayName("Teste da requisição DELETE")
	public void deleteClient() throws Exception {
		Client Cliente = new Client(1L, "Ana Carolina", "true", "12345678");
		repository.save(Cliente);
		Long id = Cliente .getId();
		boolean cli1 = repository.findById(id).isPresent();
		repository.deleteById(id);
		boolean cli2 = repository.findById(id).isPresent();
		assertTrue(cli1);
		assertFalse(cli2);
	}

}