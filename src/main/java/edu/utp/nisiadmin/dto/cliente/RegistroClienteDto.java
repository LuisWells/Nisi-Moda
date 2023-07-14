package edu.utp.nisiadmin.dto.cliente;

import edu.utp.nisiadmin.model.Cliente;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

/**
 * DTO for {@link Cliente}
 */
public record RegistroClienteDto(@NotBlank String nombre, @NotBlank String apellido, @Email @NotBlank String email,
                                 @NotBlank @Length(min = 4, max = 32) String username,
                                 @NotBlank @Length(min = 4, max = 32) String password) implements Serializable {
}