package edu.utp.nisiadmin.model;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link Compra}
 */
public record RegistroCompraDto(Long idCliente, List<ItemCompraDto> items) implements Serializable {
    /**
     * DTO for {@link ItemCompra}
     */
    public record ItemCompraDto(Long id, Long productoId, Integer cantidad, Double precio) implements Serializable {
    }
}