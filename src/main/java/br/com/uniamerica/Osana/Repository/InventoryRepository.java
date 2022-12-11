package br.com.uniamerica.Osana.Repository;

import br.com.uniamerica.Osana.Model.Inventory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface
InventoryRepository extends JpaRepository<Inventory, Long> {
    Optional<Inventory> findByName(String name);

    Page<Inventory> findByNameContains(String name, Pageable pageable);
}
