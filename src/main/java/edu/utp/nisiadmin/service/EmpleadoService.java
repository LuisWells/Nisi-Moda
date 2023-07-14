package edu.utp.nisiadmin.service;

import edu.utp.nisiadmin.dto.empleado.DetalleEmpleadoDto;
import edu.utp.nisiadmin.dto.empleado.EmpleadoMapper;
import edu.utp.nisiadmin.dto.empleado.ListaEmpleadoDto;
import edu.utp.nisiadmin.dto.empleado.RegistroEmpleadoDto;
import edu.utp.nisiadmin.enums.Estado;
import edu.utp.nisiadmin.model.Empleado;
import edu.utp.nisiadmin.repository.EmpleadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmpleadoService {
    private final EmpleadoRepository repositorio;
    private final EmpleadoMapper mapper;
    private final PasswordEncoder encoder;

    public DetalleEmpleadoDto obtenerEmpleado(Long id) {
        Optional<Empleado> posibleEmpleado = repositorio.findById(id);
        return posibleEmpleado.map(mapper::toDetalle).orElse(null);
    }

    public List<ListaEmpleadoDto> obtenerTodosLosEmpleados() {
        List<Empleado> listado = repositorio.findAll();
        return listado.stream().map(mapper::toLista).collect(Collectors.toList());
    }

    public Long crearEmpleado(RegistroEmpleadoDto dto) {
        try {
            Empleado empleado = mapper.toEntity(dto);
            empleado.setPassword(encoder.encode(dto.password()));
            empleado.setEstado(Estado.ACTIVO);
            Empleado clienteGuardado = repositorio.save(empleado);
            return clienteGuardado.getId();
        } catch (Exception e) {
            return -1L; //retorna negativo si hubo un error
        }
    }

    public boolean actualizarEmpleado(Long id, RegistroEmpleadoDto dto) {
        Optional<Empleado> posibleEmpleado = repositorio.findById(id);
        if (posibleEmpleado.isPresent()) {
            try {
                Empleado empleado = posibleEmpleado.get();
                mapper.partialUpdate(dto, empleado);
                repositorio.save(empleado);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    public boolean borrarEmpleado(Long id) {
        Optional<Empleado> posibleEmpleado = repositorio.findById(id);
        if (posibleEmpleado.isPresent()) {
            repositorio.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean dniYaExiste(String dni) {
        return false;
    }

    public boolean emailYaExiste(String email) {
        return false;
    }

    public boolean usernameYaExiste(String username) {
        return false;
    }

}
