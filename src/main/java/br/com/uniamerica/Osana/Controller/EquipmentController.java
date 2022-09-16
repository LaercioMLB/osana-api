package br.com.uniamerica.Osana.Controller;

import br.com.uniamerica.Osana.Repository.EquipmentRepository;
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
@RequestMapping("/equipment")
public class EquipmentController {

    @Autowired
    private EquipmentRepository equipmentRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<?> createEquipment(@RequestBody @Valid NewEquipmentDTO newOSDTO, UriComponentsBuilder uriComponentsBuilder){
        Optional<OS> existsOS = osRepository.findByMotive(newOSDTO.getMotive());
        if (existsOS.isPresent()){
            return ResponseEntity.badRequest().body("OS already exists");
        }
        OS newOS = newOSDTO.toModel();
        osRepository.save(newOS);
        URI uri = uriComponentsBuilder.path("/os/{id}").buildAndExpand(newOS.getIdOS()).toUri();
        return ResponseEntity.created(uri).body(new OSDTO(newOS));
    }

    @GetMapping
    public ResponseEntity<List<OS>> findAllOS(){
        List<OS> listOS = osRepository.findAll();
        return ResponseEntity.ok().body(listOS);
    }
    @GetMapping("/{id}")
    public ResponseEntity<OSDTO> findOS(@PathVariable Long id){
        Optional<OS> existsOS = osRepository.findById(id);
        if(existsOS.isPresent()){
            return ResponseEntity.ok(new OSDTO(existsOS.get()));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<OSDTO> updateOS(@PathVariable Long id, @RequestBody @Valid NewOSDTO newOSDTO){
        Optional<OS> existsOS = osRepository.findById(id);
        if(existsOS.isPresent()){
            OS newOS = newOSDTO.updatedOS(existsOS.get(),osRepository);
            return ResponseEntity.ok(new OSDTO(newOS));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deleteOS(@PathVariable Long id){
        Optional<OS> existsOS = osRepository.findById(id);
        if(existsOS.isPresent()){
            osRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
