package edu.utp.nisiadmin.repository;

import edu.utp.nisiadmin.model.ItemCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemCompraRepository extends JpaRepository<ItemCompra, Long>, JpaSpecificationExecutor<ItemCompra> {
}