package br.com.uniamerica.Osana.service;

import br.com.uniamerica.Osana.dto.input.NewOSDTO;
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
        User user = User.builder().id(1L).build();
        NewOSDTO newOSDTO = new NewOSDTO().builder().obs("observacao").userId(1L).build();
        OS os = OS.builder().obs("observacao").user(user).build();


        Mockito.doReturn(os).when(osRepository).save(any(OS.class));
        var result = osService.save(newOSDTO);
        assertThat(result).isNotNull();
        assertThat(result.getObs()).isEqualTo(newOSDTO.getObs());
    }
}
