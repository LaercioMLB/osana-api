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

import br.com.uniamerica.Osana.Model.Inventory;
import br.com.uniamerica.Osana.Repository.InventoryRepository;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class InventoryTest {
	
	@Mock
	@Autowired
	private InventoryRepository repository;
	
	@Test
	@DisplayName("Teste da requisição POST")
	public void saveInventory() throws Exception {
		Inventory Inventorye = new Inventory(1L, "Alta");
		repository.save(Inventorye);
		assertNotNull(Inventorye);
	}
	
	@Test
	@DisplayName("Teste da requisição GET")
	public void findByInventory() throws Exception {
		Inventory Inventorye1 = new Inventory
		(1L, "tal");
		Inventory Inventorye2 = new Inventory
		(1L, "tal");
		List<Inventory> Inventoryes = new ArrayList<>();
		Inventoryes.add(Inventorye1);
		Inventoryes.add(Inventorye2);
	}
	
	@Test
	@DisplayName("Teste da requisição UPDATE")
	public void UpdateInventory()throws Exception  {
	   String Name = "Alta";
	   Inventory Inventory = new Inventory(1L, "tal");
	   Inventory.setId(1L, "tal");
	   repository.save(Inventory);
	   Inventory updatedProduct = repository.findById(Name);
	   Assertions.assertEquals(updatedProduct.getName(), Name);
	}
	
	@Test
	@DisplayName("Teste da requisição DELETE")
	public void deleteInventory() throws Exception {
		Inventory inven = new Inventory(1L, "tal");
		repository.save(inven);
		Long id = inven.getId();
		boolean pri1 = repository.findById(id).isPresent();
		repository.deleteById(id);
		boolean pri2 = repository.findById(id).isPresent();
		assertTrue(pri1);
		assertFalse(pri2);
	}

}
