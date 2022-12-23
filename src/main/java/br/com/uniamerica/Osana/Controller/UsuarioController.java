package br.com.uniamerica.Osana.Controller;

import br.com.uniamerica.Osana.Config.Form.UserForm.UpdateUsuarioForm;
import br.com.uniamerica.Osana.Config.Form.UserForm.UsuarioForm;
import br.com.uniamerica.Osana.DTO.UserDTOS.UsuarioDTO;
import br.com.uniamerica.Osana.DTO.UserDTOS.UsuarioDetailedDTO;
import br.com.uniamerica.Osana.Model.Usuario;
import br.com.uniamerica.Osana.Repository.RoleRepository;
import br.com.uniamerica.Osana.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/filterByUsername")
    public ResponseEntity<UsuarioDetailedDTO> findUserByUsername(@Param("username") String username){
        Optional<Usuario> existsUser = usuarioRepository.findByUsername(username);
        if (existsUser.isPresent()){
            return ResponseEntity.ok(new UsuarioDetailedDTO(existsUser.get()));
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping
    @PreAuthorize("hasRole('ROLE_GESTOR')")
    public ResponseEntity<Page<UsuarioDTO>> findAllUsers(){
        List<Usuario> listUSers = usuarioRepository.findAll();
        Page<UsuarioDTO> listUSersDTO = new PageImpl<>(UsuarioDTO.convert(listUSers));
        return ResponseEntity.ok().body(listUSersDTO);
    }

    @GetMapping("/findByName")
    @PreAuthorize("hasRole('ROLE_GESTOR')")
    public ResponseEntity<Page<UsuarioDTO>> findByName(@RequestParam(name = "name") String name){
        List<Usuario> listUSers = usuarioRepository.findByNameContainingIgnoreCase(name);
        Page<UsuarioDTO> listUSersDTO = new PageImpl<>(UsuarioDTO.convert(listUSers));
        return ResponseEntity.ok().body(listUSersDTO);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_GESTOR')")
    public ResponseEntity<UsuarioDetailedDTO> findUserId(@PathVariable Long id){
        Optional<Usuario> user = usuarioRepository.findById(id);
        if (user.isPresent()){
            return ResponseEntity.ok(new UsuarioDetailedDTO(user.get()));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Transactional
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
        URI uri = uriComponentsBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(new UsuarioDTO(user));
    }

    @PutMapping("/{id}")
    @Transactional
    @PreAuthorize("hasRole('ROLE_GESTOR')")
    public ResponseEntity<UsuarioDetailedDTO> updateUser(@PathVariable Long id, @RequestBody @Valid UpdateUsuarioForm form){
        Optional<Usuario> user = usuarioRepository.findById(id);
        if (user.isPresent()){
            Usuario newUser = form.updateUser(user.get(), roleRepository, usuarioRepository);
            return ResponseEntity.ok(new UsuarioDetailedDTO(newUser));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    @PreAuthorize("hasRole('ROLE_GESTOR')")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        Optional<Usuario> user = usuarioRepository.findById(id);
        if (user.isPresent()){
            usuarioRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
