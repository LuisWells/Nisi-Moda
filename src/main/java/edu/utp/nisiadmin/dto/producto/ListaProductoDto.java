package edu.utp.nisiadmin.dto.producto;

import edu.utp.nisiadmin.enums.EstadoProducto;
import edu.utp.nisiadmin.model.Producto;

import java.io.Serializable;

/**
 * DTO for {@link Producto}
 */
public record ListaProductoDto(Long id, String nombre, EstadoProducto estado, String fotoUrl, String descripcion,
                               Double precio, String categoria) implements Serializable {
}