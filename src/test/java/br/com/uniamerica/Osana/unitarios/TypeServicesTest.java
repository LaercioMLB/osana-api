package br.com.uniamerica.Osana.unitarios;

import br.com.uniamerica.Osana.Controller.TypeServicesController;
import br.com.uniamerica.Osana.DTO.TypeServicesDTOS.NewTypeServicesDTO;
import br.com.uniamerica.Osana.DTO.TypeServicesDTOS.TypeServicesDTO;
import br.com.uniamerica.Osana.Model.TypeServices;
import br.com.uniamerica.Osana.Repository.TypeServicesRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class TypeServicesTest {
    @Mock
    private TypeServicesRepository typeServicesRepository;
    @InjectMocks
    private TypeServicesController typeServicesController;

    @DisplayName("Listar todos tipos de serviço")
    @Test
    void testFindAllTypeServices(){
        TypeServices typeServices = new TypeServices(1L,"MANUTENÇÃO ROTEADOR");
        typeServicesRepository.save(typeServices);

        ResponseEntity<List<TypeServices>> listTypeServices = typeServicesController.findAllTypeServices();

        assertThat(listTypeServices).isNotNull();
        assertThat(listTypeServices.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @DisplayName("Deletar tipo de serviço")
    @Test
    void testDeleteTypeServices(){
        Optional<TypeServices> typeServices = typeServicesRepository.findById(1L);
        if (typeServices.isPresent()){
            typeServicesRepository.deleteById(typeServices.get().getIdTypeServices());
        }
        Assertions.assertTrue(typeServicesRepository.findById(1L).isEmpty());
    }

    @DisplayName("Retornar tipo de serviço")
    @Test
    void testCreateTypeServices(){
        Optional<TypeServices> services = typeServicesRepository.findById(1L);
        if(services.isPresent()){
            Assertions.assertFalse(services.isEmpty());
            Assertions.assertEquals("Tipo de serviço", services.get().getServices());
        }
    }

    @DisplayName("Erro que o tipo de serviço ja existe")
    @Test
    void testTypeServiceAlreadyExists(){
        boolean exists = false;
        TypeServices newTypeServices = new TypeServices(1L,"MANUTENÇÃO ROTEADOR");
        Optional<TypeServices> typeServices = typeServicesRepository.findByServices(newTypeServices.getServices());
        if (typeServices.isPresent()){
            exists = true;
        }
        Assertions.assertEquals(true, exists);
        Assertions.assertTrue(exists);
    }


}



