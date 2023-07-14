package edu.utp.nisiadmin.dto.itemcompra;

import edu.utp.nisiadmin.model.ItemCompra;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;

/**
 * DTO for {@link ItemCompra}
 */
public record RegistroItemCompraDto(Long productoId, @Positive Integer cantidad,
                                    @Positive Double precio) implements Serializable {
}