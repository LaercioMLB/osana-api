package br.com.uniamerica.Osana.unitarios;

import br.com.uniamerica.Osana.Config.Form.UserForm.UsuarioForm;
import br.com.uniamerica.Osana.Controller.UsuarioController;
import br.com.uniamerica.Osana.Model.Role;
import br.com.uniamerica.Osana.Model.Usuario;
import br.com.uniamerica.Osana.Repository.RoleRepository;
import br.com.uniamerica.Osana.Repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class UsuarioTest {
    @Mock
    private UsuarioRepository userRepository;

    @Test
    @DisplayName("Teste de Cadastro de Usuario")
    void AddModelUsuario(){
        Usuario user = new Usuario("Wilzinho","Will", "will@gmail.com", "123456", new Role("ROLE QUALQUER"));

        Mockito.doReturn(user).when(userRepository).save(any(Usuario.class));
        Usuario userSave = userRepository.save(user);
        assertThat(userSave.getName()).isEqualTo(user.getName());
        assertThat(userSave).isNotNull();
    }
}
