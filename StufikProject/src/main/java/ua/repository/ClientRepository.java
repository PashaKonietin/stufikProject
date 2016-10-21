package ua.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Integer>, JpaSpecificationExecutor<Client> {

	@Query("SELECT c FROM Client c WHERE c.verification=:verification")
    Client findByVerification(@Param("verification") String verification);

	Client findByLogin(String login);
}
