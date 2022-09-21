package br.com.uniamerica.Osana.Repository;

import br.com.uniamerica.Osana.Model.Priority;
import br.com.uniamerica.Osana.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PriorityRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);

	Priority save(Priority prioritye);

	Priority findById(String name);

}
