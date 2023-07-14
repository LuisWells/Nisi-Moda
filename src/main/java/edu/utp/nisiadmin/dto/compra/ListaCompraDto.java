package edu.utp.nisiadmin.dto.compra;

import edu.utp.nisiadmin.model.Compra;

import java.io.Serializable;
import java.time.OffsetDateTime;

/**
 * DTO for {@link Compra}
 */
public record ListaCompraDto(Long id, Long idCliente, OffsetDateTime dateCreated) implements Serializable {
}