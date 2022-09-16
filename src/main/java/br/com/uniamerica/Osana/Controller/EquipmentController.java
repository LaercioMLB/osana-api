package br.com.uniamerica.Osana.Controller;

import br.com.uniamerica.Osana.DTO.EquipmentDTOS.EquipmentDTO;
import br.com.uniamerica.Osana.DTO.EquipmentDTOS.NewEquipmentDTO;
import br.com.uniamerica.Osana.Model.Equipment;
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
    public ResponseEntity<?> createEquipment(@RequestBody @Valid NewEquipmentDTO newEquipmentDTO, UriComponentsBuilder uriComponentsBuilder){
        Optional<Equipment> existsEquipment = equipmentRepository.findByName(newEquipmentDTO.getName());
        if (existsEquipment.isPresent()){
            return ResponseEntity.badRequest().body("EQP already exists");
        }
        Equipment newEquipment = newEquipmentDTO.toModel();
        equipmentRepository.save(newEquipment);
        URI uri = uriComponentsBuilder.path("/equipment/{id}").buildAndExpand(newEquipment.getId()).toUri();
        return ResponseEntity.created(uri).body(new EquipmentDTO(newEquipment));
    }

    @GetMapping
    public ResponseEntity<List<Equipment>> findAllEquipments(){
        List<Equipment> listEquipment = equipmentRepository.findAll();
        return ResponseEntity.ok().body(listEquipment);
    }
    @GetMapping("/{id}")
    public ResponseEntity<EquipmentDTO> findEquipment(@PathVariable Long id){
        Optional<Equipment> existsEquipment = equipmentRepository.findById(id);
        if(existsEquipment.isPresent()){
            return ResponseEntity.ok(new EquipmentDTO(existsEquipment.get()));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<EquipmentDTO> updateEquipment(@PathVariable Long id, @RequestBody @Valid NewEquipmentDTO newEquipmentDTO){
        Optional<Equipment> existsEquipment = equipmentRepository.findById(id);
        if(existsEquipment.isPresent()){
            Equipment newEquipment = newEquipmentDTO.updatedEquipment(existsEquipment.get(),equipmentRepository);
            return ResponseEntity.ok(new EquipmentDTO(newEquipment));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deleteEquipment(@PathVariable Long id){
        Optional<Equipment> existsEquipment = equipmentRepository.findById(id);
        if(existsEquipment.isPresent()){
            equipmentRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
