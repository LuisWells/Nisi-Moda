package edu.utp.nisiadmin.repository;

import edu.utp.nisiadmin.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long>, JpaSpecificationExecutor<Empleado> {
}