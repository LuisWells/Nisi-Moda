package edu.utp.nisiadmin.dto.producto;

import edu.utp.nisiadmin.enums.EstadoProducto;
import edu.utp.nisiadmin.model.Producto;

import java.io.Serializable;
import java.time.OffsetDateTime;

/**
 * DTO for {@link Producto}
 */
public record DetalleProductoDto(Long id, String nombre, Integer cantidad, EstadoProducto estado, String fotoUrl,
                                 String descripcion, Double precio, OffsetDateTime dateCreated,
                                 OffsetDateTime lastUpdated, String categoria) implements Serializable {
}