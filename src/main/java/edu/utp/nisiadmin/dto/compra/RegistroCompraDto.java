package edu.utp.nisiadmin.dto.compra;

import edu.utp.nisiadmin.dto.itemcompra.RegistroItemCompraDto;
import edu.utp.nisiadmin.model.Compra;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link Compra}
 */
public record RegistroCompraDto(Long idCliente, List<RegistroItemCompraDto> items) implements Serializable {

}