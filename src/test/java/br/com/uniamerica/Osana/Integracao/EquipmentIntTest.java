package br.com.uniamerica.Osana.Integracao;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import br.com.uniamerica.Osana.Controller.EquipmentController;
import br.com.uniamerica.Osana.Model.Equipment;
import br.com.uniamerica.Osana.Repository.EquipmentRepository;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class EquipmentIntTest {
	
	private static final String port = null;

	@Autowired
    private MockMvc mockMvc;
	
    @Autowired
    private TestRestTemplate testRestTemplate;

	@Mock
	@Autowired
	private EquipmentRepository repository;
	
	@Mock
	@Autowired
	private EquipmentController controller;
	
	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(repository).build();
		this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	@Test
    @Order(1) @RepeatedTest(1)
    @SuppressWarnings("unused")
    @DisplayName("Teste de Sucesso de Requisição, e retorno HTTP 201")
    public void shouldReturn201_WhenRegisteringEquipment() throws Exception {
    	Equipment Equipmente = new Equipment("{ \"name\": \"Ana Carolina\" }")
    	.statusCode(HttpStatus.CREATED.value());
    }
	
	@Test
    @Order(2) @RepeatedTest(1)
    @SuppressWarnings("unused")
    @DisplayName("Teste Falha de Requisição, e retorno HTTP 404")
    public void shouldReturn404_WhenFailedRegisteringEquipment() throws Exception {
    	Equipment Equipmente = new Equipment("{ \"name\": \"Ana Carolina\" }")
    	.statusCode(HttpStatus.NOT_FOUND.value());
    }
	
	@Test
    @Order(3) @RepeatedTest(1)
    @SuppressWarnings("unused")
    @DisplayName("Teste Falha de Requisição, e retorno HTTP 500")
    public void shouldReturn500_WhenFailedRegisteringEquipment() throws Exception {
    	Equipment Equipmente = new Equipment("{ \"name\": \"Ana Carolina\" }")
    	.statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
	
    @Test
    @Disabled
    @Order(4) @RepeatedTest(1)
    @DisplayName("Teste de Cadastro de Equipamento")
	public void registrationEquipment() throws Exception {
		Equipment Equipmente = new Equipment();
		repository.save(Equipmente)
        .statusCode(HttpStatus.CREATED.value());
		assertNotNull(Equipmente);
	}
    
    @Test
    @Disabled
    @Order(5) @RepeatedTest(1)
    @DisplayName("Teste de Alteracao de Equipamento")
    void shouldUpdateEquipmentById() {
        Optional<Equipment> editoraAntiga = repository.findById(1L);

        Equipment ept = new Equipment(1L, "EditoraTest222");

        this.testRestTemplate.put("http://localhost:" + port + "/Equipment/" + 1L, ept);

        Assertions.assertThat(editoraAntiga.get().getName()).isNotEqualTo(ept.getName());
    }
    
    @Test
    @Disabled
    @Order(6) @RepeatedTest(1)
    @DisplayName("Teste de Remoção de Equipamento")
    public void shouldDeleteEquipmentById() throws Exception {
    	shouldDeleteEquipmentById();
        mockMvc.perform(MockMvcRequestBuilders
        .delete("/{id}", 1L))
        .andExpect(status()
        .isNotFound());
    }

	@AfterAll
	public static void cleanUp(){ System.out.println("Only run once after all tests"); }
	
    @AfterEach
    public void cleanUpEach(){ System.out.println("Apparently all tests worked"); }
}
