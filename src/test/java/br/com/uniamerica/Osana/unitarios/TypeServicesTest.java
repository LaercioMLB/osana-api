package br.com.uniamerica.Osana.unitarios;

import br.com.uniamerica.Osana.Controller.TypeServicesController;
import br.com.uniamerica.Osana.DTO.TypeServicesDTOS.NewTypeServicesDTO;
import br.com.uniamerica.Osana.DTO.TypeServicesDTOS.TypeServicesDTO;
import br.com.uniamerica.Osana.Model.TypeServices;
import br.com.uniamerica.Osana.Repository.TypeServicesRepository;
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

//    @DisplayName("Criar um novo tipo de serviço")
//    @Test
//    void testCreateTypeServices(){
//        TypeServices typeServices = new TypeServices(4L,"FORMATAÇÃO");
//
//        ResponseEntity<TypeServices> createTypeServices = typeServicesController.createServices();
//        ResponseEntity<List<TypeServices>> listTypeServices = typeServicesController.findAllTypeServices();
//
//        assertThat(listTypeServices).isNotNull();
//    }

//    @DisplayName("Deletar um tipo de serviço")
//    @Test
//    void testDeleteTypeServices(){
//        TypeServices typeServices = new TypeServices(10L,"MANUTENAÇÃO SWITCH");
//
//        ResponseEntity<TypeServices> cad = typeServicesController.createServices(typeServices);
//
//        typeServicesRepository.save(typeServices);
//        typeServicesController.deleteTypeServices(10L);
//
//        assertThat(cad.getStatusCode()).isEqualTo(HttpStatus.OK);
//    }
}



