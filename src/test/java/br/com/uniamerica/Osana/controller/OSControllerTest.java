package br.com.uniamerica.Osana.controller;
import br.com.uniamerica.Osana.dto.OSDTO;
import br.com.uniamerica.Osana.dto.input.NewOSDTO;
import br.com.uniamerica.Osana.service.OSService;
import br.com.uniamerica.Osana.service.OSServiceTest;
import br.com.uniamerica.Osana.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.text.SimpleDateFormat;

import static br.com.uniamerica.Osana.dto.input.NewOSDTO.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OSController.class)
@ContextConfiguration
@WithMockUser
@AutoConfigureTestDatabase
public class OSControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OSService osService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private UserService userService;

    @Test
    void shouldGetAllOS() throws Exception{
        RequestBuilder request = MockMvcRequestBuilders.get("/os");
        ResultActions result = mockMvc.perform(request).andExpect(status().isOk());
        verify(osService).findAll();
        assertThat(result).isNotNull();
    }

    @Test
    void shouldAddOS() throws Exception{
        SimpleDateFormat sfd = new SimpleDateFormat("dd/MM/yyyy");
        NewOSDTO newOSDTO = new NewOSDTO().builder()
                .motive("motivo")
                .obs("obser")
                .devolution(sfd.parse("20/01/2020"))
                .dateOS(sfd.parse("20/01/2020"))
                .userId(1L)
                .build();
        String os = new ObjectMapper().writeValueAsString(newOSDTO);
        String url = "/os";
        ResultActions result = mockMvc.perform(post(url)
                .content(os)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated());
        verify(osService).save(newOSDTO);
    }
    @Test
    void shouldFindOSById() throws Exception{
        SimpleDateFormat sfd = new SimpleDateFormat("dd/MM/yyyy");
        OSDTO osDTO = OSDTO.builder()
                .idOS(1L)
                .motive("motivo")
                .obs("obser")
                .devolution(sfd.parse("20/01/2020"))
                .dateOS(sfd.parse("20/01/2020"))
                .build();

        when(osService.findById(1L)).thenReturn(osDTO);

        String url = "/os/{id}";
        mockMvc.perform(
                get(url, 1L)
        ).andExpect(status().isFound());
    }
    @Test
    void shouldUpdateOSById() throws Exception {
        SimpleDateFormat sfd = new SimpleDateFormat("dd/MM/yyyy");
        NewOSDTO newOSDTO = NewOSDTO.builder()
                .motive("motivo")
                .obs("obser")
                .devolution(sfd.parse("20/01/2020"))
                .dateOS(sfd.parse("20/01/2020"))
                .build();
        OSDTO osDTO = OSDTO.builder()
                .motive("motivo")
                .obs("obser")
                .devolution(sfd.parse("20/01/2020"))
                .dateOS(sfd.parse("20/01/2020"))
                .build();

        String json = new ObjectMapper().writeValueAsString(newOSDTO);

        when(osService.update(1, newOSDTO)).thenReturn(osDTO);

        String url = "/os/{id}";
        mockMvc.perform(
                put(url, 1L)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    void deleteById() throws Exception {
        SimpleDateFormat sfd = new SimpleDateFormat("dd/MM/yyyy");
        OSDTO osDTO = OSDTO.builder()
                .idOS(1L)
                .motive("motivo")
                .obs("obser")
                .devolution(sfd.parse("20/01/2020"))
                .dateOS(sfd.parse("20/01/2020"))
                .build();

        when(osService.deleteById(1L)).thenReturn("Successfull deleted");

        String url = "/os/{id}";
        mockMvc.perform(
                delete(url, 1L)
        ).andExpect(status().isOk());
    }
}
