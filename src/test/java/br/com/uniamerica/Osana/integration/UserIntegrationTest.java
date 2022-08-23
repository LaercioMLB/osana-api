package br.com.uniamerica.Osana.integration;
import br.com.uniamerica.Osana.dto.input.NewUserDTO;
import br.com.uniamerica.Osana.model.Role;
import br.com.uniamerica.Osana.model.User;
import br.com.uniamerica.Osana.repository.RoleRepository;
import br.com.uniamerica.Osana.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Test
    void ShouldGetAllUsersAsList() throws Exception{
        String url = "/users";
        mockMvc.perform(
                get(url)
        ).andExpect(status().isOk());
    }
    @Test
    void shouldAddUser() throws Exception {
        AddUser();
    }

    @Test
    void shouldAddUserReturnValidationExceptionIfNotValid() throws Exception {
        NewUserDTO newUserDTO = NewUserDTO.builder()
                .name("teste2")
                .build();

        var json = new ObjectMapper().writeValueAsString(newUserDTO);

        String url = "/users";
        mockMvc.perform(
                post(url)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());
    }

    @Test
    void shouldAddUserReturnExceptionIfUserNotUnique() throws Exception {
        User user = getUser();
        NewUserDTO newUserDTO = NewUserDTO.builder()
                .name("nome")
                .contact("contact")
                .password("senha")
                .username(user.getUsername())
                .build();

        var json = new ObjectMapper().writeValueAsString(newUserDTO);

        String url = "/users";
        mockMvc.perform(
                post(url)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());
    }

    @Test
    void shouldUpdateUser() throws Exception {
        NewUserDTO newUserDTO = NewUserDTO.builder()
                .name("nome")
                .contact("contact")
                .password("senha")
                .username("username")
                .build();

        var user = getUser();

        var json = new ObjectMapper().writeValueAsString(newUserDTO);

        String url = "/users/{id}";
        mockMvc.perform(
                put(url, user.getId())
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    void shouldUpdateUserReturnExceptionIfNotUniqueAndNotSameId() throws Exception {

        var user1 = getUser();
        var user2 = getUser();

        NewUserDTO newUserDTO = NewUserDTO.builder()
                .name("teste2")
                .contact("contact")
                .password("joj")
                .username(user1.getUsername())
                .build();

        var json = new ObjectMapper().writeValueAsString(newUserDTO);

        String url = "/users/{id}";
        mockMvc.perform(
                put(url, user2.getId())
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());
    }

    @Test
    void shouldUpdateUserReturnsExceptionIfNotValid() throws Exception {
        NewUserDTO newUserDTO = NewUserDTO.builder()
                .name("teste2")
                .build();

        var json = new ObjectMapper().writeValueAsString(newUserDTO);

        String url = "/users/{id}";
        mockMvc.perform(
                put(url, 1L)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());
    }

    @Test
    void shouldUpdateUserReturnsExceptionIfNotFound() throws Exception {
        NewUserDTO newUserDTO = NewUserDTO.builder()
                .name("teste2")
                .contact("contact")
                .password("senha")
                .username("username")
                .build();

        var json = new ObjectMapper().writeValueAsString(newUserDTO);

        String url = "/users/{id}";
        mockMvc.perform(
                put(url, 5000L)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound());
    }


    @Test
    void shouldFindUserById() throws Exception {
        String url = "/users/{id}";

        var user = getUser();

        mockMvc.perform(
                get(url, user.getId())
        ).andExpect(status().isOk());
    }

    @Test
    void shouldAddRole() throws Exception {
        AddRole();
    }

    @Test
    void shouldAddRoleToUser() throws Exception {
        var user = getUser();
        var role = getRole();

        var json = new ObjectMapper().writeValueAsString(role);

        String url = "/users/{id}/role";
        mockMvc.perform(
                post(url, user.getId())
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated());
    }

    @Test
    void shouldDeleteById() throws Exception {
        String url = "/users/{id}";

        var user = getUser();

        mockMvc.perform(
                delete(url, user.getId())
        ).andExpect(status().isOk());
    }

    private MvcResult AddUser() throws Exception {
        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, StandardCharsets.UTF_8);

        NewUserDTO newUserDTO = NewUserDTO.builder()
                .name("teste2")
                .contact("contact")
                .password("senha")
                .username(generatedString)
                .build();


        var json = new ObjectMapper().writeValueAsString(newUserDTO);

        String url = "/users";
        return mockMvc.perform(
                        post(url)
                                .content(json)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                ).andExpect(status().isCreated())
                .andReturn();
    }

    private User getUser() throws Exception {
        var mvcResult = AddUser();
        return new ObjectMapper().readValue(mvcResult.getResponse().getContentAsByteArray(), User.class);
    }

    private MvcResult AddRole() throws Exception {
        Role role = Role.builder()
                .name("admin")
                .build();

        var json = new ObjectMapper().writeValueAsString(role);

        String url = "/users/roles";
        return mockMvc.perform(
                        post(url)
                                .content(json)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                ).andExpect(status().isCreated())
                .andReturn();
    }

    private Role getRole() throws Exception {
        var mvcResult = AddRole();
        return new ObjectMapper().readValue(mvcResult.getResponse().getContentAsByteArray(), Role.class);
    }

    @BeforeEach
    private void beforeAll() {
        userRepository.deleteAll();
        roleRepository.deleteAll();
    }
}
