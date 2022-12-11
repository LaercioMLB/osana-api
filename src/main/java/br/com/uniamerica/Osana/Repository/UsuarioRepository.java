package br.com.uniamerica.Osana.Repository;

import br.com.uniamerica.Osana.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsername(String username);

    List<Usuario> findByNameContains(String name);
}