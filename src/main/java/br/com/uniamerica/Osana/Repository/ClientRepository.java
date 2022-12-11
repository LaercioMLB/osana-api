package br.com.uniamerica.Osana.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.uniamerica.Osana.Model.Client;

@Repository
public interface ClientRepository extends JpaRepository <Client, Long> {
	Page<Client> findByContract(String contract, Pageable pageable);

	Optional<Client> findByFirstName(String name);

}
