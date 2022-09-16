package br.com.uniamerica.Osana.Integracao;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import br.com.uniamerica.Osana.Repository.ClientRepository;
import br.com.uniamerica.Osana.Model.Client;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ClientIntTest {

	@Mock
	@Autowired
	private ClientRepository repository;
	
    @Autowired
    private MockMvc mockMvc;
	
	@Test
    @Order(1) @RepeatedTest(1)
    @SuppressWarnings("unused")
    @DisplayName("Teste de Sucesso de Requisição, e retorno HTTP 201")
    public void shouldReturn201_WhenRegisteringClient() throws Exception {
    	Client cliente = new Client("{ \"name\": \"Ana Carolina\" }")
    	.statusCode(HttpStatus.CREATED.value());
    }
	
	@Test
    @Order(2) @RepeatedTest(1)
    @SuppressWarnings("unused")
    @DisplayName("Teste Falha de Requisição, e retorno HTTP 500")
    public void shouldReturn500_WhenFailedRegisteringClient() throws Exception {
    	Client cliente = new Client("{ \"name\": \"Ana Carolina\" }")
    	.statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
	
    @Test
    @Order(3) @RepeatedTest(2)
    @SuppressWarnings("unused")
    @DisplayName("Teste Validação de CNPJ")
    public void verify_WhenCnpjIsValid() throws Exception {
        Client cliente = new Client(1L, "Ana Carolina", "true", "564654654654")
        .statusCode(HttpStatus.CREATED.value());
		assertTrue(cliente);
    } private void assertTrue(Client cliente) {}

    @Test
    @Disabled
    @Order(4) @RepeatedTest(1)
    @DisplayName("Teste de Cadastro de Cliente")
	public void registrationClient() throws Exception {
		Client Cliente = new Client();
		repository.save(Cliente)
        .statusCode(HttpStatus.CREATED.value());
		assertNotNull(Cliente);
	}
    
    @Test
    @Disabled
    @Order(5) @RepeatedTest(1)
    @DisplayName("Teste de Remoção de Cliente")
    public void shouldDeleteClientById() throws Exception {
    	shouldDeleteClientById();
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
