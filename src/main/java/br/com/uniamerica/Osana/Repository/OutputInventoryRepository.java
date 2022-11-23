package br.com.uniamerica.Osana.Repository;

import br.com.uniamerica.Osana.Model.Inventory;
import br.com.uniamerica.Osana.Model.OutputInventory;
import br.com.uniamerica.Osana.Model.OutputInventoryKey;
import br.com.uniamerica.Osana.Model.Priority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OutputInventoryRepository extends JpaRepository<OutputInventory, OutputInventoryKey> {
}
