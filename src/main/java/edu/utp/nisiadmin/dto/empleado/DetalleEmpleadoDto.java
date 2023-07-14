package edu.utp.nisiadmin.dto.empleado;

import edu.utp.nisiadmin.enums.Estado;
import edu.utp.nisiadmin.enums.Rol;
import edu.utp.nisiadmin.model.Empleado;

import java.io.Serializable;
import java.time.OffsetDateTime;

/**
 * DTO for {@link Empleado}
 */
public record DetalleEmpleadoDto(Long id, String nombre, String apellido, String dni, String direccion, String email,
                                 String telefono, Estado estado, String username, String fotoUrl, Rol rol,
                                 OffsetDateTime dateCreated, OffsetDateTime lastUpdated) implements Serializable {
}