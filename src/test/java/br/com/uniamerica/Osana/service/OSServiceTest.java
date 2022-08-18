package br.com.uniamerica.Osana.service;

import br.com.uniamerica.Osana.dto.OSDTO;
import br.com.uniamerica.Osana.dto.input.NewOSDTO;
import br.com.uniamerica.Osana.dto.input.NewUserDTO;
import br.com.uniamerica.Osana.model.OS;
import br.com.uniamerica.Osana.model.User;
import br.com.uniamerica.Osana.repository.OSRepository;
import br.com.uniamerica.Osana.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import static org.mockito.ArgumentMatchers.any;
@ExtendWith(MockitoExtension.class)
public class OSServiceTest {
    @Mock
    private OSRepository osRepository;
    @Mock
    private UserRepository userRepository;

    OSService osService;

    @BeforeEach
    void setUp(){
        osService = new OSService(osRepository, userRepository);
    }
    @Test
    void ShouldAddOS(){
        NewOSDTO newOSDTO = NewOSDTO.builder().obs("observacao").build();
        OS os = OS.builder().obs("observacao").build();
        Mockito.doReturn(os).when(osRepository).save(any(OS.class));

        var result = osService.save(newOSDTO);

        assertThat(result).isNotNull();
        assertThat(result.getObs()).isEqualTo(newOSDTO.getObs());
    }

    @Test
    void ShouldFindOSById(){
        OSDTO osDTO = OSDTO.builder().idOS(1L).obs("observacao").build();
        OS os = OS.builder().idOS(1L).obs("observacao").build();

        Mockito.when(osRepository.findById(osDTO.getIdOS())).thenReturn(Optional.of(os));

        var result=osService.findById(os.getIdOS());
        assertThat(result).isEqualTo(osDTO);

    }
    @Test
    void ShouldUpdateOS(){
        NewOSDTO newOSDTO = NewOSDTO.builder().obs("observamento").build();
        OS os = OS.builder().idOS(1L).obs("observamento").build();

        Mockito.when(osRepository.findById(os.getIdOS())).thenReturn(Optional.of(os));

        var result = osService.update(os.getIdOS(), newOSDTO);

        assertThat(result).isNotNull().isEqualTo(new OSDTO(os));
        assertThat(result.getIdOS()).isEqualTo(os.getIdOS());
    }
    @Test
    void shouldUpdateOSReturnsExceptionIfNotFound(){
        NewOSDTO newOSDTO = NewOSDTO.builder().obs("obs").build();
        OS os = OS.builder().idOS(1L).obs("obs").build();

        Mockito.when(osRepository.findById(any())).thenReturn(Optional.empty());
        assertThatThrownBy(() -> osService.update(os.getIdOS(), newOSDTO)).isInstanceOf(IllegalStateException.class);
    }
    @Test
    void shouldDeleteById() {
        OS os = OS.builder()
                .idOS(1L)
                .obs("teste")
                .build();

        Mockito.when(osRepository.findById(os.getIdOS())).thenReturn(Optional.of(os));

        var result = osService.deleteById(os.getIdOS());

        assertThat(result).isEqualTo("OS was successfully deleted");
    }
    @Test
    void shouldDeleteByIdReturnsThrowsException() {
        OS os = OS.builder()
                .idOS(1L)
                .obs("teste")
                .build();

        Mockito.when(osRepository.findById(any())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> osService.deleteById(os.getIdOS())).isInstanceOf(IllegalStateException.class);
    }
}
