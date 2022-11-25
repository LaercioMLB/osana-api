package br.com.uniamerica.Osana.Controller;

import br.com.uniamerica.Osana.Config.RegraNegocioException;
import br.com.uniamerica.Osana.DTO.InventoryDTOS.InventoryDTO;
import br.com.uniamerica.Osana.DTO.InventoryDTOS.InventoryToOSDTO;
import br.com.uniamerica.Osana.DTO.OSDTOS.NewOSDTO;
import br.com.uniamerica.Osana.DTO.OSDTOS.OSDTO;
import br.com.uniamerica.Osana.DTO.UserDTOS.UsuarioDTO;
import br.com.uniamerica.Osana.DTO.UserDTOS.UsuarioDetailedDTO;
import br.com.uniamerica.Osana.Model.*;
import br.com.uniamerica.Osana.Repository.*;
import br.com.uniamerica.Osana.Services.OSservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/os")
public class OSController {
    @Autowired
    private OSservice oSservice;
    @Autowired
    private OSRepository osRepository;
    @Autowired
    private OutputInventoryRepository outputInventoryRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<?> createOS(@RequestBody @Valid NewOSDTO newOSDTO, UriComponentsBuilder uriComponentsBuilder){
        OS newOS = oSservice.createOS(newOSDTO);
        URI uri = uriComponentsBuilder.path("/os/{id}").buildAndExpand(newOS.getIdOS()).toUri();
        return ResponseEntity.created(uri).body(new OSDTO(newOS));
    }

    @GetMapping
    public ResponseEntity<Page<OS>> findAllOS(Pageable pageable){
        Page<OS> listOS = osRepository.findAll(pageable);
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

    @GetMapping("/findOSByUser/{id}")
    public ResponseEntity<List<OS>> findOSbyUser(@PathVariable Long id){
        Optional<Usuario> user = usuarioRepository.findById(id);
        if (user.isPresent()){
            List<OS> listOsFind = osRepository.findByUsuario(user.get());
            return ResponseEntity.ok(listOsFind);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<OSDTO> updateOS(@PathVariable Long id, @RequestBody @Valid NewOSDTO newOSDTO){
        OS existsOS = oSservice.updateOS(id, newOSDTO);
        return ResponseEntity.ok(new OSDTO(existsOS));
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
