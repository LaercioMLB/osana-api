package br.com.uniamerica.Osana.Integracao;

import static org.junit.Assert.*;

import java.net.URI;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.google.gson.Gson;

import br.com.uniamerica.Osana.DTO.UserDTOS.TokenDTO;
import br.com.uniamerica.Osana.Model.Usuario;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserIntTest {

    @Autowired
    private MockMvc mockMvc;

    private String validToken;

    public UserIntTest() {
    }

    @BeforeEach
    public void initEach() throws Exception{
        URI uri = new URI("/auth");
        String json = "{\"username\": \"desenv gestor\", \"password\":\"123456\"}";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post(uri)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        String responseBody = mvcResult.getResponse().getContentAsString();
        TokenDTO tokenResponse = new Gson().fromJson(responseBody, TokenDTO.class);
        this.validToken = tokenResponse.getToken();
    }
    
	@Test
    @Order(1) @RepeatedTest(1)
    @SuppressWarnings("unused")
    @DisplayName("Teste de Sucesso de Requisição, e retorno HTTP 201")
    public void shouldReturn201_WhenRegisteringUser() throws Exception {
		Usuario user = new Usuario("{ \"name\": \"Ana Carolina\" }")
    	.statusCode(HttpStatus.CREATED.value());
    }
	
	@Test
    @Order(2) @RepeatedTest(1)
    @SuppressWarnings("unused")
    @DisplayName("Teste Falha de Requisição, e retorno HTTP 404")
    public void shouldReturn404_WhenFailedRegisteringUser() throws Exception {
		Usuario user = new Usuario("{ \"name\": \"Ana Carolina\" }")
    	.statusCode(HttpStatus.NOT_FOUND.value());
    }
	
	@Test
    @Order(3) @RepeatedTest(1)
    @SuppressWarnings("unused")
    @DisplayName("Teste Falha de Requisição, e retorno HTTP 500")
    public void shouldReturn500_WhenFailedRegisteringUser() throws Exception {
		Usuario user = new Usuario("{ \"name\": \"Ana Carolina\" }")
    	.statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    @Test
    @Order(4)
    @DisplayName("Deve Retornar erro, pois não esta autenticado !!")
    public void shouldReturnStatus403ForbiddenNotAuthorization() throws Exception{
        URI uri = new URI("/users");
        mockMvc.perform(MockMvcRequestBuilders
             .get(uri)
             .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().is(403));
    }

    @Test
    @Order(5)
    @DisplayName("Deve Buscar Um Usuario Por ID !!")
    public void shouldListUserWithIdWithSuccess() throws Exception{
        URI uri = new URI("/users/2");
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get(uri)
                        .header("Authorization", "Bearer " + validToken))
                        .andExpect(MockMvcResultMatchers.status().is(200))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.roles[*].id").isNotEmpty())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2))
                        .andReturn();
        String responseBody = result.getResponse().getContentAsString();
        Usuario userResponse = new Gson().fromJson(responseBody, Usuario.class);
        assertFalse(responseBody.isEmpty());
        assertTrue(responseBody.contains("roles"));
        assertEquals(200, result.getResponse().getStatus());
        assertEquals(true, userResponse.isEnabled());
    }

    @Test
    @Order(6)
    @DisplayName("Deve Listar Todos Usuarios !!")
    public void shouldListAllUsersWithSuccess() throws Exception{
        URI uri = new URI("/users");
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .get(uri)
                .header("Authorization", "Bearer " + validToken))
                .andExpect(MockMvcResultMatchers.status().is(200)).andReturn();
        String responseBody = result.getResponse().getContentAsString();
        assertFalse(responseBody.isEmpty());
        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    @Order(7)
    @DisplayName("Deve Criar um Usuario !!")
    public void shouldCreateUserTecnicoWithSuccess() throws Exception{
        String json = "{\"name\": \"Bob Brown\","
        		+ "\"email\":\"bob@gmail.com\","
        		+ "\"username\": \"Bob2\","
        		+ "\"password\":\"123456\","
        		+ "\"typeUser\": \"ROLE_TECNICO\"}";
        URI uri = new URI("/users");
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                    .post(uri)
                    .header("Authorization", "Bearer " + validToken)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.roles[*].name").value("ROLE_TECNICO"))
                    .andExpect(MockMvcResultMatchers.status().is(201)).andReturn();
        String responseBody = result.getResponse().getContentAsString();
        Usuario userResponse = new Gson().fromJson(responseBody, Usuario.class);
        assertFalse(responseBody.isEmpty());
        assertTrue(responseBody.contains("roles"));
        assertEquals("bob@gmail.com",userResponse.getEmail());
    }

    @Test
    @Order(8)
    @DisplayName("Deve Alterar um Usuario !!")
    public void shouldUpdateUserTecnicoWithSuccess() throws Exception{
        String json = "{\"name\": \"Bob Brown\","
        		+ "\"email\":\"bob@gmail.com\","
        		+ "\"username\": \"Bob\","
        		+ "\"typeUser\": \"ROLE_GESTOR\"}";
        URI uri = new URI("/users/2");
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .put(uri)
                .header("Authorization", "Bearer " + validToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)).andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.roles").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.roles[*].id").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2))
                .andReturn();
        String responseBody = result.getResponse().getContentAsString();
        Usuario userResponse = new Gson().fromJson(responseBody, Usuario.class);
        assertFalse(responseBody.isEmpty());
        assertTrue(responseBody.contains("roles"));
        assertEquals("Bob Brown", userResponse.getName());
    }

    @Test
    @Order(9)
    @DisplayName("Deve Deletar um Usuario !!")
    public void shouldDeleteUserWithSuccess() throws Exception{
        URI uri = new URI("/users/2");
        mockMvc.perform(MockMvcRequestBuilders
              .delete(uri)
              .header("Authorization", "Bearer " + validToken))
        .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    @Order(10)
    @DisplayName("Retorna Erro de Username Existente !!")
    public void shouldReturnErrorExistsUsername() throws Exception{
        String json = "{\"name\": \"Bob Brown\","
        		+ " \"email\":\"bob@gmail.com\""
        		+ ",\"username\": \"desenv gestor\""
        		+ ", \"password\":\"123456\""
        		+ ",\"typeUser\": \"ROLE_TECNICO\"}";
        URI uri = new URI("/users");
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .post(uri)
                .header("Authorization", "Bearer " + validToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)).andExpect(MockMvcResultMatchers.status().is(400)).andReturn();
        String responseBody = result.getResponse().getContentAsString();
        assertFalse(responseBody.isEmpty());
        assertEquals("Username já esta em Uso!!",responseBody);
    }

    @Test
    @Order(11)
    @DisplayName("Retorna Erro de Role Inexistente !!")
    public void shouldReturnErrorNotExistsRole() throws Exception{
        String json = "{\"name\": \"Bob Brown\","
        		+ " \"email\":\"bob@gmail.com\","
        		+ "\"bobzinho\": \"desenv gestor\","
        		+ "\"password\":\"123456\","
        		+ "\"typeUser\": \"ROLE_QUALQUER\"}";
        URI uri = new URI("/users");
        mockMvc.perform(MockMvcRequestBuilders
                .post(uri)
                .header("Authorization", "Bearer " + validToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)).andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    @Order(12)
    @DisplayName("Retorna Erro de Alterar Usuario inexistente !!")
    public void shouldReturnErrorUpdateNotExistsUser() throws Exception{
        String json = "{\"name\": \"Bob Brown\","
        		+ "\"email\":\"bob@gmail.com\","
        		+ "\"username\": \"bobinho\","
        		+ "\"password\":\"123456\","
        		+ "\"typeUser\": \"ROLE_TECNICO\"}";
        URI uri = new URI("/users/99");
        mockMvc.perform(MockMvcRequestBuilders
                .put(uri)
                .header("Authorization", "Bearer " + validToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)).andExpect(MockMvcResultMatchers.status().is(404));
    }

    @Test
    @Order(13)
    @DisplayName("Retorna Erro de Deletar Usuario inexistente !!")
    public void shouldReturnErrorDeleteNotExistsUser() throws Exception{
        URI uri = new URI("/users/99");
        mockMvc.perform(MockMvcRequestBuilders
                .delete(uri)
                .header("Authorization", "Bearer " + validToken)).andExpect(MockMvcResultMatchers.status().is(404));
    }
    
	@AfterAll
	public static void cleanUp(){ System.out.println("Only run once after all tests"); }
	
    @AfterEach
    public void cleanUpEach(){ System.out.println("Apparently all tests worked"); }
}