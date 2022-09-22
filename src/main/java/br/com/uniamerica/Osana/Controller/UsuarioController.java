package br.com.uniamerica.Osana.Controller;

import br.com.uniamerica.Osana.Config.Form.UserForm.UpdateUsuarioForm;
import br.com.uniamerica.Osana.Config.Form.UserForm.UsuarioForm;
import br.com.uniamerica.Osana.DTO.UserDTOS.UsuarioDTO;
import br.com.uniamerica.Osana.DTO.UserDTOS.UsuarioDetailedDTO;
import br.com.uniamerica.Osana.Model.Usuario;
import br.com.uniamerica.Osana.Repository.RoleRepository;
import br.com.uniamerica.Osana.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_GESTOR')")
    public ResponseEntity<List<UsuarioDTO>> findAllUsers(){
        List<Usuario> listUSers = usuarioRepository.findAll();
        List<UsuarioDTO> listUSersDTO = UsuarioDTO.convert(listUSers);
        return ResponseEntity.status(HttpStatus.OK).body(listUSersDTO);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_GESTOR')")
    public ResponseEntity<UsuarioDetailedDTO> findUserId(@PathVariable Long id){
        Optional<Usuario> user = usuarioRepository.findById(id);
        if (user.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(new UsuarioDetailedDTO(user.get()));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping @Transactional
    @PreAuthorize("hasRole('ROLE_GESTOR')")
    public ResponseEntity<?> createUser(@RequestBody @Valid UsuarioForm form, UriComponentsBuilder uriComponentsBuilder){
        Optional<Usuario> existsUser = usuarioRepository.findByUsername(form.getUsername());
        if (existsUser.isPresent()){
            return ResponseEntity.badRequest().body("Username já esta em Uso!!");
        }
        Usuario user = form.convert(roleRepository);
        if(user == null){
            return ResponseEntity.badRequest().build();
        }
        usuarioRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(new UsuarioDTO(user));
    }

    @PutMapping("/{id}") @Transactional
    @PreAuthorize("hasRole('ROLE_GESTOR')")
    public ResponseEntity<UsuarioDetailedDTO> updateUser(@PathVariable Long id, @RequestBody @Valid UpdateUsuarioForm form){
        Optional<Usuario> user = usuarioRepository.findById(id);
        if (user.isPresent()){
            Usuario newUser = form.updateUser(user.get(), roleRepository, usuarioRepository);
            return ResponseEntity.ok(new UsuarioDetailedDTO(newUser));
        }else{
            return ResponseEntity.status(HttpStatus.OK).build();
        }
    }

    @DeleteMapping("/{id}") @Transactional
    @PreAuthorize("hasRole('ROLE_GESTOR')")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        Optional<Usuario> user = usuarioRepository.findById(id);
        if (user.isPresent()){
            usuarioRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
