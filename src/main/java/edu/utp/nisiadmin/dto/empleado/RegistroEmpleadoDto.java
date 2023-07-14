package edu.utp.nisiadmin.dto.empleado;

import edu.utp.nisiadmin.enums.Rol;
import edu.utp.nisiadmin.model.Empleado;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

/**
 * DTO for {@link Empleado}
 */
public record RegistroEmpleadoDto(@NotBlank String nombre, @NotBlank String apellido, @NotBlank String dni,
                                  String direccion, @Email @NotBlank String email, String telefono,
                                  @NotBlank @Length(min = 4, max = 24) String username,
                                  @NotBlank @Length(min = 4, max = 32) String password, String fotoUrl,
                                  @NotNull Rol rol) implements Serializable {
}