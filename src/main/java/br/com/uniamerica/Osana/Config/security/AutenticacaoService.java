package br.com.uniamerica.Osana.Config.security;

import br.com.uniamerica.Osana.Model.Usuario;
import br.com.uniamerica.Osana.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class AutenticacaoService implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> user = usuarioRepository.findByUsername(username);
        if (user.isPresent()){
            return user.get();
        }

        throw new UsernameNotFoundException("Dados Inválidos !");
    }
}
