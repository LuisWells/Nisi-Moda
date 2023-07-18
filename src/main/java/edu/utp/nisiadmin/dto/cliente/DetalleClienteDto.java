package edu.utp.nisiadmin.dto.cliente;

import edu.utp.nisiadmin.enums.Estado;
import edu.utp.nisiadmin.model.Cliente;

import java.io.Serializable;
import java.time.OffsetDateTime;

/**
 * DTO for {@link Cliente}
 */
public record DetalleClienteDto(Long id, String nombre, String apellido, String email, String dni, Estado estado,
                                OffsetDateTime dateCreated, OffsetDateTime lastUpdated) implements Serializable {
}