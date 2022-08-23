package br.com.uniamerica.Osana.controller;

import br.com.uniamerica.Osana.dto.UserDTO;
import br.com.uniamerica.Osana.dto.input.NewUserDTO;
import br.com.uniamerica.Osana.model.Role;
import br.com.uniamerica.Osana.model.User;
import br.com.uniamerica.Osana.service.RoleService;
import br.com.uniamerica.Osana.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.security.test.context.support.WithMockUser;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
@ContextConfiguration
@WithMockUser
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;
    @MockBean
    RoleService roleService;
    @MockBean
    private PasswordEncoder passwordEncoder;

    private UserController userController;

    @Test
    void listItems() throws Exception {
        List<UserDTO> users = new ArrayList<UserDTO>();
        when(userService.findAll()).thenReturn(users);

        String url = "/users";
        mockMvc.perform(
                get(url)
        ).andExpect(status().isOk());
    }

    @Test
    void addItem() throws Exception{
        NewUserDTO newUserDTO = NewUserDTO.builder()
                .name("testee")
                .contact("testContact")
                .password("testPass")
                .username("testUser")
                .build();
        UserDTO userDTO = UserDTO.builder()
                .name("testee")
                .contact("testContact")
                .username("testUser")
                .build();

        var json = new ObjectMapper().writeValueAsString(newUserDTO);

        when(userService.save(newUserDTO)).thenReturn(userDTO);

        String url = "/users";
        mockMvc.perform(
                post(url)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated());
    }
    @Test
    void update() throws Exception {
        NewUserDTO newUserDTO = NewUserDTO.builder()
                .name("testee")
                .contact("testContact")
                .password("testPass")
                .username("testUser")
                .build();
        UserDTO userDTO = UserDTO.builder()
                .idUser(1L)
                .name("testee")
                .contact("testContact")
                .username("testUser")
                .build();

        var json = new ObjectMapper().writeValueAsString(newUserDTO);

        when(userService.update(1, newUserDTO)).thenReturn(userDTO);

        String url = "/users/{id}";
        mockMvc.perform(
                put(url, 1L)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    void findById() throws Exception {
        UserDTO userDTO = UserDTO.builder()
                .idUser(1L)
                .name("teste")
                .build();

        when(userService.findById(1L)).thenReturn(userDTO);

        String url = "/users/{id}";
        mockMvc.perform(
                get(url, 1L)
        ).andExpect(status().isOk());
    }

    @Test
    void deleteById() throws Exception {
        UserDTO userDTO = UserDTO.builder()
                .idUser(1L)
                .name("teste")
                .build();

        when(userService.deleteById(1L)).thenReturn("Deletado com Sucesso");

        String url = "/users/{id}";
        mockMvc.perform(
                delete(url, 1L)
        ).andExpect(status().isOk());
    }

    @Test
    void saveRole() throws Exception {
        Role role = Role.builder()
                .name("admin")
                .build();

        var json = new ObjectMapper().writeValueAsString(role);

        when(roleService.save(role)).thenReturn(role);

        String url = "/users/roles";
        mockMvc.perform(
                post(url)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated());
    }

    @Test
    void addRoleToUser() throws Exception {
        Role role = Role.builder()
                .name("admin")
                .build();

        var json = new ObjectMapper().writeValueAsString(role);

        String url = "/users/{id}/role";
        mockMvc.perform(
                post(url, 1L)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated());
    }
}
