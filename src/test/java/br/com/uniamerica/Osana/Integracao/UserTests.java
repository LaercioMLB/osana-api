package br.com.uniamerica.Osana.Integracao;
import br.com.uniamerica.Osana.DTO.UserDTOS.TokenDTO;
import br.com.uniamerica.Osana.Model.Usuario;
import com.google.gson.Gson;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

import java.net.URI;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserTests {

    @Autowired
    private MockMvc mockMvc;

    private String validToken;

    public UserTests() {
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
    @Order(1)
    @DisplayName("Deve Retornar erro, pois não esta autenticado !!")
    public void sholdReturnStatus403ForbiddenNotAuthorization() throws Exception{
        URI uri = new URI("/users");

        mockMvc.perform(MockMvcRequestBuilders
                        .get(uri)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(403));
    }

    @Test
    @Order(2)
    @DisplayName("Deve Buscar Um Usuario Por ID !!")
    public void sholdListUserWithIdWithSuccess() throws Exception{
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
    @Order(3)
    @DisplayName("Deve Listar Todos Usuarios !!")
    public void sholdListAllUsersWithSuccess() throws Exception{
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
    @Order(4)
    @DisplayName("Deve Criar um Usuario !!")
    public void sholdCreateUserTecnicoWithSuccess() throws Exception{
        String json = "{\"name\": \"Bob Brown\", \"email\":\"bob@gmail.com\",\"username\": \"Bob2\", \"password\":\"123456\",\"typeUser\": \"ROLE_TECNICO\"}";
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
    @Order(5)
    @DisplayName("Deve Alterar um Usuario !!")
    public void sholdUpdateUserTecnicoWithSuccess() throws Exception{
        String json = "{\"name\": \"Bob Brown\", \"email\":\"bob@gmail.com\",\"username\": \"Bob\", \"typeUser\": \"ROLE_GESTOR\"}";
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
    @Order(6)
    @DisplayName("Deve Deletar um Usuario !!")
    public void sholdDeleteUserWithSuccess() throws Exception{
        URI uri = new URI("/users/2");

        mockMvc.perform(MockMvcRequestBuilders
                        .delete(uri)
                        .header("Authorization", "Bearer " + validToken))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    @Order(7)
    @DisplayName("Retorna Erro de Username Existente !!")
    public void sholdReturnErrorExistsUsername() throws Exception{
        String json = "{\"name\": \"Bob Brown\", \"email\":\"bob@gmail.com\",\"username\": \"desenv gestor\", \"password\":\"123456\",\"typeUser\": \"ROLE_TECNICO\"}";
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
    @Order(8)
    @DisplayName("Retorna Erro de Role Inexistente !!")
    public void sholdReturnErrorNotExistsRole() throws Exception{
        String json = "{\"name\": \"Bob Brown\", \"email\":\"bob@gmail.com\",\"bobzinho\": \"desenv gestor\", \"password\":\"123456\",\"typeUser\": \"ROLE_QUALQUER\"}";
        URI uri = new URI("/users");

        mockMvc.perform(MockMvcRequestBuilders
                .post(uri)
                .header("Authorization", "Bearer " + validToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)).andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    @Order(9)
    @DisplayName("Retorna Erro de Alterar Usuario inexistente !!")
    public void sholdReturnErrorUpdateNotExistsUser() throws Exception{
        String json = "{\"name\": \"Bob Brown\", \"email\":\"bob@gmail.com\",\"username\": \"bobinho\", \"password\":\"123456\",\"typeUser\": \"ROLE_TECNICO\"}";
        URI uri = new URI("/users/99");

        mockMvc.perform(MockMvcRequestBuilders
                .put(uri)
                .header("Authorization", "Bearer " + validToken)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)).andExpect(MockMvcResultMatchers.status().is(404));
    }

    @Test
    @Order(10)
    @DisplayName("Retorna Erro de Deletar Usuario inexistente !!")
    public void sholdReturnErrorDeleteNotExistsUser() throws Exception{
        URI uri = new URI("/users/99");

        mockMvc.perform(MockMvcRequestBuilders
                .delete(uri)
                .header("Authorization", "Bearer " + validToken)).andExpect(MockMvcResultMatchers.status().is(404));
    }
}