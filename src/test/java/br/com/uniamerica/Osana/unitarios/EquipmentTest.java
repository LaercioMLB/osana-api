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

import br.com.uniamerica.Osana.Model.Equipment;
import br.com.uniamerica.Osana.Repository.EquipmentRepository;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class EquipmentTest {
	
	@Mock
	@Autowired
	private EquipmentRepository repository;
	
	@Test
	@DisplayName("Teste da requisição POST")
	public void saveEquipament() throws Exception {
		Equipment Equipamente = new Equipment(1L, "tal", "tal");
		repository.save(Equipamente);
		assertNotNull(Equipamente);
	}
	
	@Test
	@DisplayName("Teste da requisição GET")
	public void findByEquipament() throws Exception {
		Equipment Equipamente1 = new Equipment
		(1L, "tal", "tal");
		Equipment Equipamente2 = new Equipment
		(1L, "tal", "tal");
		List<Equipment> Equipamentes = new ArrayList<>();
		Equipamentes.add(Equipamente1);
		Equipamentes.add(Equipamente2);
	}
	
	@Test
	@DisplayName("Teste da requisição UPDATE")
	public void UpdateEquipament()throws Exception  {
	   String Name = "Alta";
	   Equipment Equipament = new Equipment(1L, "tal", "tal");
	   Equipament.setId(1L, "tal");
	   repository.save(Equipament);
	   Equipment updatedProduct = repository.findById(Name);
	   Assertions.assertEquals(updatedProduct.getName(), Name);
	}
	
	@Test
	@DisplayName("Teste da requisição DELETE")
	public void deleteEquipament() throws Exception {
		Equipment inven = new Equipment(1L, "tal", "tal");
		repository.save(inven);
		Long id = inven.getId();
		boolean pri1 = repository.findById(id).isPresent();
		repository.deleteById(id);
		boolean pri2 = repository.findById(id).isPresent();
		assertTrue(pri1);
		assertFalse(pri2);
	}

}
