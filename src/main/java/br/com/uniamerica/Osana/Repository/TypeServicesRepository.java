package br.com.uniamerica.Osana.Repository;

import br.com.uniamerica.Osana.Model.TypeServices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TypeServicesRepository extends JpaRepository<TypeServices,Long>{
    Optional<TypeServices> findByServices(String typeServices);
}
