package br.com.uniamerica.Osana.Repository;

import br.com.uniamerica.Osana.Model.Status;
import jdk.jshell.Snippet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StatusRepository extends JpaRepository<Status, Long> {
    Optional<Status> findByName(String name);

}
