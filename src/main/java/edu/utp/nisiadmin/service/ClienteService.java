package edu.utp.nisiadmin.service;

import edu.utp.nisiadmin.dto.cliente.ClienteMapper;
import edu.utp.nisiadmin.dto.cliente.DetalleClienteDto;
import edu.utp.nisiadmin.dto.cliente.ListaClienteDto;
import edu.utp.nisiadmin.dto.cliente.RegistroClienteDto;
import edu.utp.nisiadmin.enums.Estado;
import edu.utp.nisiadmin.model.Cliente;
import edu.utp.nisiadmin.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClienteService {
    private final ClienteRepository repositorio;
    private final ClienteMapper mapper;
    private final PasswordEncoder encoder;


    public DetalleClienteDto obtenerCliente(Long id) {
        Optional<Cliente> posibleCliente = repositorio.findById(id);
        return posibleCliente.map(mapper::toDetalle).orElse(null);
    }

    public List<ListaClienteDto> obtenerTodosLosClientes() {
        List<Cliente> listado = repositorio.findAll();
        return listado.stream().map(mapper::toLista).collect(Collectors.toList());
    }

    public Long crearCliente(RegistroClienteDto dto) {
        try {
            Cliente cliente = mapper.toEntity(dto);
            cliente.setPassword(encoder.encode(dto.password()));
            cliente.setEstado(Estado.ACTIVO);
            Cliente clienteGuardado = repositorio.save(cliente);
            return clienteGuardado.getId();
        } catch (Exception e) {
            return -1L; //retorna negativo si hubo un error
        }
    }

    public boolean actualizarCliente(Long id, RegistroClienteDto dto) {
        Optional<Cliente> posibleCliente = repositorio.findById(id);
        if (posibleCliente.isPresent()) {
            try {
                Cliente cliente = posibleCliente.get();
                mapper.partialUpdate(dto, cliente);
                repositorio.save(cliente);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    public boolean borrarCliente(Long id) {
        Optional<Cliente> posibleCliente = repositorio.findById(id);
        if (posibleCliente.isPresent()) {
            repositorio.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean emailYaExiste(String email) {
        return false;
    }

    public boolean usernameYaExiste(String username) {
        return false;
    }
}
