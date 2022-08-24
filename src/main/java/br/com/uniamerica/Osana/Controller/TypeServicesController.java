package br.com.uniamerica.Osana.Controller;

import br.com.uniamerica.Osana.DTO.TypeServicesDTOS.NewTypeServicesDTO;
import br.com.uniamerica.Osana.DTO.TypeServicesDTOS.TypeServicesDTO;
import br.com.uniamerica.Osana.Model.TypeServices;
import br.com.uniamerica.Osana.Repository.TypeServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/services")
public class TypeServicesController {
    @Autowired
    private TypeServicesRepository typeServicesRepository;

    @GetMapping
    public ResponseEntity<List<TypeServices>> findAllTypeServices(){
        List<TypeServices> listTypeServices = typeServicesRepository.findAll();
        return ResponseEntity.ok().body(listTypeServices);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> createServices(@RequestBody @Valid NewTypeServicesDTO newTypeServicesDTO, UriComponentsBuilder uriComponentsBuilder){
        Optional<TypeServices> existsServices = typeServicesRepository.findByServices(newTypeServicesDTO.getServices());
        if (existsServices.isPresent()){
            return ResponseEntity.badRequest().body("Type of service already exists");
        }
        TypeServices newTypeServices = newTypeServicesDTO.toModel();
        typeServicesRepository.save(newTypeServices);
        URI uri = uriComponentsBuilder.path("/services/{id}").buildAndExpand(newTypeServices.getIdTypeServices()).toUri();
        return ResponseEntity.created(uri).body(new TypeServicesDTO(newTypeServices));
    }
}
