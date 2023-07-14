package edu.utp.nisiadmin.dto.compra;

import edu.utp.nisiadmin.model.Compra;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;

/**
 * DTO for {@link Compra}
 */
public record DetalleCompraDto(Long id, Long idCliente, List<Long> itemIds, OffsetDateTime dateCreated,
                               OffsetDateTime lastUpdated) implements Serializable {
}