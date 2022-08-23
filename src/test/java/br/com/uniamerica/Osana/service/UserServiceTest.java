package br.com.uniamerica.Osana.service;
import br.com.uniamerica.Osana.dto.UserDTO;
import br.com.uniamerica.Osana.dto.input.NewUserDTO;
import br.com.uniamerica.Osana.exception.ResourceNotFoundException;
import br.com.uniamerica.Osana.exception.UniqueException;
import br.com.uniamerica.Osana.model.Role;
import br.com.uniamerica.Osana.model.User;
import br.com.uniamerica.Osana.repository.RoleRepository;
import br.com.uniamerica.Osana.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository, passwordEncoder, roleRepository);
    }

    @Test
    void shouldAddUser() {

        NewUserDTO newUserDTO = NewUserDTO.builder()
                .name("teste")
                .build();
        User user = User.builder()
                .name("teste")
                .build();

//        Mockito.when(userRepository.save(user)).thenReturn(user);
        Mockito.doReturn(user).when(userRepository).save(any(User.class));

        var result = userService.save(newUserDTO);

        assertThat(result)
                .isNotNull();
        assertThat(result.getName())
                .isEqualTo(newUserDTO.getName());
    }

    @Test
    void shouldFindUserById() {
        UserDTO userdto = UserDTO.builder()
                .idUser(1L)
                .name("teste")
                .build();
        User user = User.builder()
                .id(1L)
                .name("teste")
                .build();

        Mockito.when(userRepository.findById(userdto.getIdUser())).thenReturn(Optional.of(user));

        var result = userService.findById(user.getId());

        assertThat(result).isEqualTo(userdto);
    }


    @Test
    void shouldUpdateUser() {
        NewUserDTO newUserDTO = NewUserDTO.builder()
                .name("teste2")
                .build();
        User user = User.builder()
                .id(1L)
                .name("teste")
                .build();

        Mockito.when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        var result = userService.update(user.getId(), newUserDTO);

        assertThat(result)
                .isNotNull()
                .isEqualTo(new UserDTO(user));
        assertThat(result.getIdUser())
                .isEqualTo(user.getId());

    }

    @Test
    void shouldUpdateUserReturnsExceptionIfNotFound(){
        NewUserDTO newUserDTO = NewUserDTO.builder()
                .name("teste2")
                .build();
        User user = User.builder()
                .id(1L)
                .name("teste")
                .build();

        Mockito.when(userRepository.findById(any())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.update(user.getId(), newUserDTO)).isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    void shouldDeleteById() {
        User user = User.builder()
                .id(1L)
                .name("teste")
                .build();

        Mockito.when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        var result = userService.deleteById(user.getId());

        assertThat(result)
                .isEqualTo("Deletado com Sucesso");
    }

    @Test
    void shouldDeleteByIdReturnsThrowsException() {
        User user = User.builder()
                .id(1L)
                .name("teste")
                .build();

        Mockito.when(userRepository.findById(any())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.deleteById(user.getId())).isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    void shouldReturnAllUsers() {
        User user = User.builder()
                .id(1L)
                .name("teste")
                .build();
        Mockito.when(userRepository.findAll()).thenReturn(List.of(user));

        var result = userService.findAll();

        List<UserDTO> users = new ArrayList<>();
        users.add(new UserDTO(user));
        assertThat(result).isEqualTo(users);
    }

    @Test
    void shouldLoadUserByUsername(){
        Collection<Role> roles = new ArrayList<>();
        User user = User.builder()
                .id(1L)
                .name("teste")
                .username("teste")
                .password("teste")
                .roles(roles)
                .build();

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        Mockito.when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.of(user));

        var result = userService.loadUserByUsername(user.getUsername());

        assertThat(result).isEqualTo(new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities));
    }

    @Test
    void shouldAddRoleToUser(){

        Collection<Role> roles = new ArrayList<>();

        Role role = Role.builder()
                .id(1L)
                .name("admin")
                .build();

        roles.add(role);

        User user = User.builder()
                .id(1L)
                .name("teste")
                .username("teste")
                .password("teste")
                .roles(roles)
                .build();

        Mockito.when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        userService.addRoleToUser(user.getId(), role.getName());

        assertThat(user.getRoles().iterator().next()).isEqualTo(role);

    }

    @Test
    void shouldReturnUniqueExceptionIfUserNotUnique() {
        User user = User.builder()
                .id(1L)
                .name("teste")
                .username("teste")
                .password("teste")
                .build();

        Mockito.when(userRepository.findByUsername(any())).thenReturn(Optional.of(user));

        assertThatThrownBy(() -> userService.ifUsernameExistsReturnException("teste")).isInstanceOf(UniqueException.class);
        assertThatThrownBy(() -> userService.ifUsernameExistsReturnException("teste", 2L)).isInstanceOf(UniqueException.class);
    }
}