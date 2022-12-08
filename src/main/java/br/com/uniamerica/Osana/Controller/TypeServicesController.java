package br.com.uniamerica.Osana.Controller;

import br.com.uniamerica.Osana.DTO.TypeServicesDTOS.NewTypeServicesDTO;
import br.com.uniamerica.Osana.DTO.TypeServicesDTOS.TypeServicesDTO;
import br.com.uniamerica.Osana.Model.Inventory;
import br.com.uniamerica.Osana.Model.TypeServices;
import br.com.uniamerica.Osana.Repository.TypeServicesRepository;
import org.apache.tomcat.util.net.openssl.OpenSSLConf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @GetMapping
    public ResponseEntity<Page<TypeServices>> findAll(Pageable pageable){
        Page<TypeServices> listTypeServices = typeServicesRepository.findAll(pageable);
        return ResponseEntity.ok().body(listTypeServices);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<TypeServices>> findAllTypeServices(){
        List<TypeServices> listTypeServices = typeServicesRepository.findAll();
        return ResponseEntity.ok().body(listTypeServices);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypeServicesDTO> findTypeServices(@PathVariable Long id){
        Optional<TypeServices> existsServices = typeServicesRepository.findById(id);
        if(existsServices.isPresent()){
            return ResponseEntity.ok(new TypeServicesDTO(existsServices.get()));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TypeServicesDTO> updateTypeServices(@PathVariable Long id, @RequestBody @Valid NewTypeServicesDTO newTypeServicesDTO){
        Optional<TypeServices> existsServices = typeServicesRepository.findById(id);
        if(existsServices.isPresent()){
            TypeServices newTypeServices = newTypeServicesDTO.updateServices(existsServices.get(),typeServicesRepository);
            return ResponseEntity.ok(new TypeServicesDTO(newTypeServices));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deleteTypeServices(@PathVariable Long id){
        Optional<TypeServices> existsServices = typeServicesRepository.findById(id);
        if(existsServices.isPresent()){
            typeServicesRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
