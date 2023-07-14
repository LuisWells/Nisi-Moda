package edu.utp.nisiadmin.service;

import edu.utp.nisiadmin.dto.producto.DetalleProductoDto;
import edu.utp.nisiadmin.dto.producto.ListaProductoDto;
import edu.utp.nisiadmin.dto.producto.ProductoMapper;
import edu.utp.nisiadmin.dto.producto.RegistroProductoDto;
import edu.utp.nisiadmin.enums.EstadoProducto;
import edu.utp.nisiadmin.model.Producto;
import edu.utp.nisiadmin.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductoService {
    private final ProductoRepository repositorio;
    private final ProductoMapper mapper;

    public DetalleProductoDto obtenerProducto(Long id) {
        Optional<Producto> posibleProducto = repositorio.findById(id);
        return posibleProducto.map(mapper::toDetalle).orElse(null);
    }

    public List<ListaProductoDto> obtenerTodosLosProductos() {
        List<Producto> listado = repositorio.findAll();
        return listado.stream().map(mapper::toLista).toList();
    }

    public Long crearProducto(RegistroProductoDto dto) {
        try {
            Producto producto = mapper.toEntity(dto);
            producto.setEstado(EstadoProducto.NUEVO);
            Producto productoGuardado = repositorio.save(producto);
            return productoGuardado.getId();
        } catch (Exception e) {
            return -1L; //retorna negativo si hubo un error
        }
    }

    public boolean actualizarProducto(Long id, RegistroProductoDto dto) {
        Optional<Producto> posibleProducto = repositorio.findById(id);
        if (posibleProducto.isPresent()) {
            try {
                mapper.partialUpdate(dto, posibleProducto.get());
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    public boolean borrarProducto(Long id) {
        Optional<Producto> posibleProducto = repositorio.findById(id);
        if (posibleProducto.isPresent()) {
            repositorio.deleteById(id);
            return true;
        }
        return false;
    }
}
