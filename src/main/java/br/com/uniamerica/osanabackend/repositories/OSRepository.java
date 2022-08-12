package br.com.uniamerica.osanabackend.repositories;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.uniamerica.osanabackend.entities.OS;

public interface OSRepository extends JpaRepository<OS, Long>{
    List<OS> findByDateOS (Date dateOS);
}
