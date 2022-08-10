package br.com.uniamerica.osanabackend.services;

import br.com.uniamerica.osanabackend.entities.Usuario;
import br.com.uniamerica.osanabackend.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository userRepository;

    public List<Usuario> findAll(){
        return userRepository.findAll();
    }

    public Usuario findById(Long id){
        Optional<Usuario> obj = userRepository.findById(id);
        return obj.get();
    }

    public Usuario insert(Usuario obj){
        return userRepository.save(obj);
    }

    public void delete(Long id){
        userRepository.deleteById(id);
    }

    public Usuario update(Long id, Usuario obj){
        Usuario entity = userRepository.getReferenceById(id);
        updateData(entity, obj);
        return userRepository.save(entity);
    }

    private void updateData(Usuario entity, Usuario obj) {
        entity.setName(obj.getName());
        entity.setEmail(obj.getEmail());
        entity.setPhone(obj.getPhone());
    }
}
