package br.com.uniamerica.Osana.Repository;

import br.com.uniamerica.Osana.Model.OS;
import br.com.uniamerica.Osana.Model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface OSRepository extends JpaRepository<OS, Long> {
    Optional<OSRepository> findByDateOS(Date dateOS);
    Optional<OS> findByMotive(String motive); ///PROVISORIO!!!
    Page<OS> findByUsuario(Usuario user, Pageable pageable);

}

