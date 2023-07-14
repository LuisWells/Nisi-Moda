package edu.utp.nisiadmin.dto.empleado;

import edu.utp.nisiadmin.enums.Estado;
import edu.utp.nisiadmin.enums.Rol;
import edu.utp.nisiadmin.model.Empleado;

import java.io.Serializable;

/**
 * DTO for {@link Empleado}
 */
public record ListaEmpleadoDto(Long id, String nombre, String apellido, Estado estado,
                               Rol rol) implements Serializable {
}