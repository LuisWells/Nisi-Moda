package edu.utp.nisiadmin.repository;

import edu.utp.nisiadmin.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>, JpaSpecificationExecutor<Cliente> {
    boolean existsByEmailIgnoreCaseAndDni(String email, String dni);

    Optional<Cliente> findByEmailIgnoreCaseAndDni(String email, String dni);
}