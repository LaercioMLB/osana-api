package br.com.uniamerica.Osana.repository;

import br.com.uniamerica.Osana.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}