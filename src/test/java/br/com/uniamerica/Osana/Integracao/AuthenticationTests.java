package br.com.uniamerica.Osana.Integracao;


import br.com.uniamerica.Osana.DTO.UserDTOS.TokenDTO;
import com.google.gson.Gson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthenticationTests {

    @Autowired
    private MockMvc mockMvc;

    public String returnTokenTecnico() throws Exception{
        URI uri = new URI("/auth");
        String json = "{\"username\": \"desenv tecnico\", \"password\":\"123456\"}";

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post(uri)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();
        TokenDTO tokenResponse = new Gson().fromJson(responseBody, TokenDTO.class);
        return tokenResponse.getToken();
    }

    @Test
    @DisplayName("Deve Retornar Bad Request, dados invalidos")
    public void sholdReturnBadRequest400ForInvalidDatas() throws Exception{
        URI uri = new URI("/auth");
        String json = "{\"username\": \"invalido\", \"password\":\"123456\"}";

        mockMvc.perform(MockMvcRequestBuilders
                .post(uri)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    @DisplayName("Deve Authenticar corretamente !!")
    public void sholdReturnStatus200() throws Exception{
        URI uri = new URI("/auth");
        String json = "{\"username\": \"desenv gestor\", \"password\":\"123456\"}";

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().is(200)).andReturn();

        String content = result.getResponse().getContentAsString();
        Assertions.assertFalse(content.isEmpty());
        Assertions.assertTrue(content.contains("token"));
    }

    @Test
    @DisplayName("Deve Retornar Erro, erro de autorização!!")
    public void sholdReturnAllUsers() throws Exception{
        String validToken = returnTokenTecnico();
        URI uri = new URI("/users");

        mockMvc.perform(MockMvcRequestBuilders
                .get(uri)
                .header("Authorization", "Bearer " + validToken))
                .andExpect(MockMvcResultMatchers.status().is(403));
    }
}
