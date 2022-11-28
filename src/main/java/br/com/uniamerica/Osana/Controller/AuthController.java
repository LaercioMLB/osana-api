package br.com.uniamerica.Osana.Controller;

import br.com.uniamerica.Osana.Config.Form.UserForm.LoginForm;
import br.com.uniamerica.Osana.Config.RegraNegocioException;
import br.com.uniamerica.Osana.Config.security.TokenService;
import br.com.uniamerica.Osana.DTO.OSDTOS.OSDTO;
import br.com.uniamerica.Osana.DTO.UserDTOS.TokenDTO;
import br.com.uniamerica.Osana.DTO.UserDTOS.UsuarioDTO;
import br.com.uniamerica.Osana.Model.OS;
import br.com.uniamerica.Osana.Model.Usuario;
import br.com.uniamerica.Osana.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private UsuarioDTO buscaUsuario(String username){
        Optional<Usuario> existsUSer = usuarioRepository.findByUsername(username);
        if(existsUSer.isPresent()){
            return new UsuarioDTO(existsUSer.get());
        }else{
            return null;
        }
    }

    @PostMapping
    public ResponseEntity<TokenDTO> authenticate(@RequestBody @Valid LoginForm form){
        UsernamePasswordAuthenticationToken loginData = form.converter();
        try{
            Authentication authentication = authManager.authenticate(loginData);
            String token = tokenService.gerarToken(authentication);
            UsuarioDTO user = buscaUsuario(form.getUsername());
            return ResponseEntity.ok(new TokenDTO(token, "Bearer", user));
        }catch (AuthenticationException e){
            throw new RegraNegocioException("Usuário não existe, Confira os dados do Formulário");
        }
    }

}
