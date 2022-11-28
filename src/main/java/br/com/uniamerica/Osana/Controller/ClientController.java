package br.com.uniamerica.Osana.Controller;

import br.com.uniamerica.Osana.DTO.ClientDTOS.ClientDTO;
import br.com.uniamerica.Osana.DTO.ClientDTOS.NewClientDTO;
import br.com.uniamerica.Osana.Model.Client;
import br.com.uniamerica.Osana.Repository.ClientRepository;
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
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<?> createClient(@RequestBody @Valid NewClientDTO newClientDTO, UriComponentsBuilder uriComponentsBuilder){
        Optional<Client> existsClient = clientRepository.findByFirstName(newClientDTO.getFirstName());
        if (existsClient.isPresent()){
            return ResponseEntity.badRequest().body("Client already exists");
        }
        Client newClient = newClientDTO.toModel();
        clientRepository.save(newClient);
        URI uri = uriComponentsBuilder.path("/client/{id}").buildAndExpand(newClient.getId()).toUri();
        return ResponseEntity.created(uri).body(new ClientDTO(newClient));
    }

    @GetMapping
    public ResponseEntity<List<Client>> findAllClients(){
        List<Client> listClient = clientRepository.findAll();
        return ResponseEntity.ok().body(listClient);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> findClient(@PathVariable Long id){
        Optional<Client> existsClient = clientRepository.findById(id);
        if(existsClient.isPresent()){
            return ResponseEntity.ok(new ClientDTO(existsClient.get()));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ClientDTO> updateClient(@PathVariable Long id, @RequestBody @Valid NewClientDTO newClientDTO){
        Optional<Client> existsClient = clientRepository.findById(id);
        if(existsClient.isPresent()){
            Client newClient = newClientDTO.updatedClient(existsClient.get(),clientRepository);
            return ResponseEntity.ok(new ClientDTO(newClient));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deleteClient(@PathVariable Long id){
        Optional<Client> existsClient = clientRepository.findById(id);
        if(existsClient.isPresent()){
            clientRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}