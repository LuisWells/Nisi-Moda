package edu.utp.nisiadmin.model;

import java.io.Serializable;
import java.time.OffsetDateTime;

/**
 * DTO for {@link Compra}
 */
public record ListaCompraDto(Long id, Long idCliente, OffsetDateTime dateCreated) implements Serializable {
}