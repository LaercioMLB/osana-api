package br.com.uniamerica.Osana.Controller;

import br.com.uniamerica.Osana.DTO.InventoryDTOS.InventoryDTO;
import br.com.uniamerica.Osana.DTO.InventoryDTOS.NewInventoryDTO;
import br.com.uniamerica.Osana.Model.Inventory;
import br.com.uniamerica.Osana.Repository.InventoryRepository;
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
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryRepository inventoryRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<?> createInventory(@RequestBody @Valid NewInventoryDTO newInventoryDTO, UriComponentsBuilder uriComponentsBuilder){
        Optional<Inventory> existsInventory = inventoryRepository.findByName(newInventoryDTO.getName());
        if (existsInventory.isPresent()){
            return ResponseEntity.badRequest().body("Inventory already exists");
        }
        Inventory newInventory = newInventoryDTO.toModel();
        inventoryRepository.save(newInventory);
        URI uri = uriComponentsBuilder.path("/inventory/{id}").buildAndExpand(newInventory.getId()).toUri();
        return ResponseEntity.created(uri).body(new InventoryDTO(newInventory));
    }

    @GetMapping
    public ResponseEntity<Page<Inventory>> findAllInventory(Pageable pageable){
        Page<Inventory> listInventory = inventoryRepository.findAll(pageable);
        return ResponseEntity.ok().body(listInventory);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryDTO> findInventory(@PathVariable Long id){
        Optional<Inventory> existsInventory = inventoryRepository.findById(id);
        if(existsInventory.isPresent()){
            return ResponseEntity.ok(new InventoryDTO(existsInventory.get()));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<InventoryDTO> updateInventory(@PathVariable Long id, @RequestBody @Valid NewInventoryDTO newInventoryDTO){
        Optional<Inventory> existsInventory = inventoryRepository.findById(id);
        if(existsInventory.isPresent()){
            Inventory newInventory = newInventoryDTO.updatedInventory(existsInventory.get(),inventoryRepository);
            return ResponseEntity.ok(new InventoryDTO(newInventory));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deleteInventory(@PathVariable Long id){
        Optional<Inventory> existsInventory = inventoryRepository.findById(id);
        if(existsInventory.isPresent()){
            inventoryRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
