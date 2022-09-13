package br.com.uniamerica.Osana.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.uniamerica.Osana.Model.Client;

@Repository
public interface ClientRepository extends JpaRepository <Client, Long> {

	Client findByName(String name);

}
