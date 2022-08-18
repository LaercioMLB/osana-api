package br.com.uniamerica.Osana.service;

import br.com.uniamerica.Osana.dto.UserDTO;
import br.com.uniamerica.Osana.dto.input.NewUserDTO;
import br.com.uniamerica.Osana.model.Role;
import br.com.uniamerica.Osana.model.User;
import br.com.uniamerica.Osana.repository.RoleRepository;
import br.com.uniamerica.Osana.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import br.com.uniamerica.Osana.exception.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
   // private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public List<UserDTO> findAll() {
        return userRepository.findAll().stream().map(UserDTO::new).collect(Collectors.toList());
    }

    public UserDTO save(NewUserDTO newUserDTO) {
        ifUsernameExistsReturnException(newUserDTO.getUsername());
        newUserDTO.setPassword(newUserDTO.getPassword());
        return new UserDTO(userRepository.save(newUserDTO.toModel()));
    }

    public UserDTO findById(long id) {
        User user = findUserByIdOrReturnException(id);
        return new UserDTO(user);
    }

    public UserDTO update(long id, NewUserDTO newUserDTO) {
        User user = findUserByIdOrReturnException(id);
        ifUsernameExistsReturnException(newUserDTO.getUsername(), id);
        user.setName(newUserDTO.getName());
        user.setContact(newUserDTO.getContact());
        user.setUsername(newUserDTO.getUsername());
        return new UserDTO(user);
    }

    public String deleteById(long id) {
        User user = findUserByIdOrReturnException(id);
        userRepository.deleteById(user.getId());
        return "Deletado com Sucesso";
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("user not found "));
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    public void addRoleToUser(Long userId, String roleName){
        User user = findUserByIdOrReturnException(userId);
        Role role = roleRepository.findByName(roleName);

        if(!user.getRoles().contains(role)){
            user.getRoles().add(role);
        }
    }

    public User findUserByIdOrReturnException(Long userId){
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Not Found User by ID:" + userId));
    }

    public void ifUsernameExistsReturnException(String username, Long id){
        var user = userRepository.findByUsername(username);
        if(user.isPresent() && !user.get().getId().equals(id)){
            throw new UniqueException("Usuario já Existente");
        }
    }

    public void ifUsernameExistsReturnException(String username){
        var user = userRepository.findByUsername(username);
        if(user.isPresent()){
            throw new UniqueException("Usuario já Existente");
        }
    }
}
