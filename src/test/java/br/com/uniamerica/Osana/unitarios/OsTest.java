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

import br.com.uniamerica.Osana.Model.OS;
import br.com.uniamerica.Osana.Repository.OSRepository;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class OsTest {
	
	@Mock
	@Autowired
	private OSRepository repository;
	
	@Test
	@DisplayName("Teste da requisição POST")
	public void saveOS() throws Exception {
		OS OSe = new OS(1L, "Dev", "alta", "2022-02-02", "2021-02-02");
		repository.save(OSe);
		assertNotNull(OSe);
	}
	
	@Test
	@DisplayName("Teste da requisição GET")
	public void findByOS() throws Exception {
		OS OSe1 = new OS
		(1L, "Dev", "alta", "2022-02-02", "2021-02-02");
		OS OSe2 = new OS
		(1L, "Dev", "alta", "2022-02-02", "2021-02-02");
		List<OS> OSes = new ArrayList<>();
		OSes.add(OSe1);
		OSes.add(OSe2);
	}
	
	@Test
	@DisplayName("Teste da requisição UPDATE")
	public void updateOS() throws Exception {
		String name = "Ana Carolina";
		OS Membro = new OS(1L, "Dev", "alta", "2022-02-02", "2021-02-02");
		repository.save(Membro);
		OS OSe = repository.findById(1L).get();
		OSe.setMotive(name);
		OS liga = repository.save(OSe);
		assertEquals(liga.getMotive(),name);
	}
	
	@Test
	@DisplayName("Teste da requisição DELETE")
	public void deleteOS() throws Exception {
		OS OSe = new OS(1L, "Dev", "alta", "2022-02-02", "2021-02-02");
		repository.save(OSe);
		Long id = OSe .getIdOS();
		boolean cli1 = repository.findById(id).isPresent();
		repository.deleteById(id);
		boolean cli2 = repository.findById(id).isPresent();
		assertTrue(cli1);
		assertFalse(cli2);
	}

}
