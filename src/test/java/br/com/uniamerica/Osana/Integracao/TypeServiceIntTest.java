package br.com.uniamerica.Osana.Integracao;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
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
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import br.com.uniamerica.Osana.Model.TypeServices;
import br.com.uniamerica.Osana.Repository.TypeServicesRepository;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class TypeServiceIntTest {
	
	@Autowired
    private MockMvc mockMvc;

	@Mock
	@Autowired
	private TypeServicesRepository repository;
	
	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(repository).build();
	}
	
	@Test
    @Order(1) @RepeatedTest(1)
    @SuppressWarnings("unused")
    @DisplayName("Teste de Sucesso de Requisição, e retorno HTTP 201")
    public void shouldReturn201_WhenRegisteringTypeServices() throws Exception {
		TypeServices TypeServices = new TypeServices("{ \"name\": \"Ana Carolina\" }")
    	.statusCode(HttpStatus.CREATED.value());
    }
	
	@Test
    @Order(2) @RepeatedTest(1)
    @SuppressWarnings("unused")
    @DisplayName("Teste Falha de Requisição, e retorno HTTP 404")
    public void shouldReturn404_WhenFailedRegisteringTypeServices() throws Exception {
    	TypeServices TypeServices = new TypeServices("{ \"name\": \"Alta\" }")
    	.statusCode(HttpStatus.NOT_FOUND.value());
    }
	
	@Test
    @Order(3) @RepeatedTest(1)
    @SuppressWarnings("unused")
    @DisplayName("Teste Falha de Requisição, e retorno HTTP 500")
    public void shouldReturn500_WhenFailedRegisteringTypeServices() throws Exception {
    	TypeServices TypeServices = new TypeServices("{ \"name\": \"Alta\" }")
    	.statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    @Test
    @Order(4) @RepeatedTest(1)
    @DisplayName("Teste de Cadastro de TypeServiceIntTeste")
	public void registrationTypeServices() throws Exception {
    	TypeServices TypeServices = new TypeServices();
		repository.save(TypeServices)
        .statusCode(HttpStatus.CREATED.value());
		assertNotNull(TypeServices);
	}
    
    @Test
    @Order(5) @RepeatedTest(1)
    @DisplayName("Teste de Remoção de Tipo de Serviço")
    public void shouldDeleteTypeServices() throws Exception {
    	shouldDeleteTypeServices();
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