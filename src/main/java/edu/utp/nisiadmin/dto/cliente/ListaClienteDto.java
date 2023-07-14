package edu.utp.nisiadmin.dto.cliente;

import edu.utp.nisiadmin.enums.Estado;
import edu.utp.nisiadmin.model.Cliente;

import java.io.Serializable;

/**
 * DTO for {@link Cliente}
 */
public record ListaClienteDto(Long id, String nombre, String apellido, String email,
                              Estado estado) implements Serializable {
}