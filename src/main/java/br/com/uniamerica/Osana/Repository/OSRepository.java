package br.com.uniamerica.Osana.Repository;

import br.com.uniamerica.Osana.Model.OS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface OSRepository extends JpaRepository<OS, Long> {
    Optional<OSRepository> findByDateOS(Date dateOS);
    Optional<OS> findByMotive(String motive); ///PROVISORIO!!!
    Optional<OSRepository> findByUser (Long idUsuario);

}

