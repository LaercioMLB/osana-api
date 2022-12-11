package br.com.uniamerica.Osana.Repository;

import br.com.uniamerica.Osana.Model.Equipment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface
EquipmentRepository extends JpaRepository<Equipment, Long> {
    Optional<Equipment> findByName(String name);

    Page<Equipment> findByNameContains(String name, Pageable pageable);
}
