package br.com.uniamerica.Osana.unitarios;

import br.com.uniamerica.Osana.Config.Form.UserForm.UpdateUsuarioForm;
import br.com.uniamerica.Osana.Model.Role;
import br.com.uniamerica.Osana.Model.Usuario;
import br.com.uniamerica.Osana.Repository.RoleRepository;
import br.com.uniamerica.Osana.Repository.UsuarioRepository;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserTest {

	@Mock
    @Autowired
    private UsuarioRepository usuarioRepository;

	@Mock
    @Autowired
    private RoleRepository roleRepository;

    @Test
    @Order(1)
    @DisplayName("Deve Retornar uma lista de usuários !!")
    public void shoudReturnAllUsers(){
        List<Usuario> list_users = usuarioRepository.findAll();
        Assertions.assertFalse(list_users.isEmpty());
        Assertions.assertTrue(list_users.get(0).getId() == 1);
        Assertions.assertTrue(list_users.size() == 2);
    }

    @Test
    @Order(2)
    @DisplayName("Deve Retornar o usuario gestor cadastrado !!")
    public void returnOneUserWithId(){
        Optional<Usuario> user = usuarioRepository.findById(1L);
        if(user.isPresent()){
            Assertions.assertFalse(user.isEmpty());
            Assertions.assertEquals("Desenvolvedor Account", user.get().getName());
        }
    }

    @Test
    @Order(3)
    @DisplayName("Deve Adicionar um usuario gestor validando username !!")
    public void createNewUserGestorWithValidationUsername(){
        Usuario newUser = new Usuario("bobzinho", "desenv gestor 2", "bob@gmail.com", "123456", new Role("ROLE_GESTOR"));
        Optional<Usuario> user = usuarioRepository.findByUsername(newUser.getUsername());
        if (!user.isPresent()){
            usuarioRepository.save(newUser);
        }
        Usuario searchNewUser = usuarioRepository.findByUsername(newUser.getUsername()).get();
        Assertions.assertTrue(searchNewUser.getName() == "bobzinho");
    }

    @Test
    @Order(4)
    @DisplayName("Deve Retornar erro por usuário ja existir !!")
    public void sholdReturnErrorForExistsUsername(){
        boolean exists = false;
        Usuario newUser = new Usuario("bobzinho", "desenv gestor", "bob@gmail.com", "123456", new Role("ROLE_GESTOR"));
        Optional<Usuario> user = usuarioRepository.findByUsername(newUser.getUsername());
        if (user.isPresent()){ exists = true;
        } Assertions.assertEquals(true, exists);
          Assertions.assertTrue(exists);
    }

    @Test
    @Order(5)
    @DisplayName("Alterar um usuario !!")
    public void sholdUpdateUser(){
        UpdateUsuarioForm newUser = new UpdateUsuarioForm("bobzinho", "desenv gestor", "bob@gmail.com", "ROLE_GESTOR");
        Optional<Usuario> userGestor = usuarioRepository.findById(1L);
        if (userGestor.isPresent()){
            newUser.updateUser(userGestor.get(), roleRepository, usuarioRepository);
        }

        Usuario user = usuarioRepository.findById(1L).get();

        Assertions.assertEquals("bobzinho", user.getName());
        Assertions.assertEquals("bob@gmail.com", user.getEmail());
    }

    @Test
    @Order(6)
    @DisplayName("Deletar um usuario !!")
    public void sholdDeleteUser(){
        Optional<Usuario> userTecnico = usuarioRepository.findById(2L);
        if (userTecnico.isPresent()){
            usuarioRepository.deleteById(userTecnico.get().getId());
        }
        Assertions.assertTrue(usuarioRepository.findById(2L).isEmpty());
    }
}
