package br.com.uniamerica.osanabackend.repositories;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.uniamerica.osanabackend.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{
    List<Client> findByContract (Boolean contract);
    List<Client> findByNameContaining (String name);
}
