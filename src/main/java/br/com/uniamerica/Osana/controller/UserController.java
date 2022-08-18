package br.com.uniamerica.Osana.controller;

import br.com.uniamerica.Osana.dto.UserDTO;
import br.com.uniamerica.Osana.dto.input.NewUserDTO;
import br.com.uniamerica.Osana.model.Role;
import br.com.uniamerica.Osana.service.RoleService;
import br.com.uniamerica.Osana.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final RoleService roleService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> listAll(){
        return userService.findAll();
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO create (@Valid @RequestBody NewUserDTO newUserDTO) {
        return userService.save(newUserDTO);
    }
    @GetMapping(path = {"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public UserDTO findById(@PathVariable long id){
        return userService.findById(id);
    }
    @PutMapping(value="/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO update(@PathVariable("id") long id,
                          @Valid @RequestBody NewUserDTO newUserDTO) {
        return userService.update(id, newUserDTO);
    }
    @DeleteMapping(path ={"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public String delete(@PathVariable("id") long id) {
        return userService.deleteById(id);
    }
    @PostMapping("/roles")
    public ResponseEntity<Role> saveRole(@RequestBody Role role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/users/roles").toUriString());
        return ResponseEntity.created(uri).body(roleService.save(role));
    }
    @PostMapping("{id}/role")
    public ResponseEntity<?> addRoleToUser(@RequestBody Role role, @PathVariable long id) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/users/roles").toUriString());
        userService.addRoleToUser(id, role.getName());
        return ResponseEntity.created(uri).build();
    }
}
