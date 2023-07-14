package edu.utp.nisiadmin.repository;

import edu.utp.nisiadmin.model.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CompraRepository extends JpaRepository<Compra, Long>, JpaSpecificationExecutor<Compra> {
}