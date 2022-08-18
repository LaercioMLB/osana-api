package br.com.uniamerica.Osana.repository;

import br.com.uniamerica.Osana.model.OS;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;

public interface OSRepository extends JpaRepository<OS, Long> {
    OS findAllByDateOS (Date dateOS);

}
