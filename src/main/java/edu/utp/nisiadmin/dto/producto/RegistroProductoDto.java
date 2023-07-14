package edu.utp.nisiadmin.dto.producto;

import edu.utp.nisiadmin.model.Producto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

import java.io.Serializable;

/**
 * DTO for {@link Producto}
 */
public record RegistroProductoDto(@NotBlank String nombre, @PositiveOrZero Integer cantidad, String fotoUrl,
                                  @NotBlank String descripcion, @PositiveOrZero Double precio,
                                  String categoria) implements Serializable {
}