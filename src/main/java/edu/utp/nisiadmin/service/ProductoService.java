package edu.utp.nisiadmin.service;

import edu.utp.nisiadmin.dto.producto.DetalleProductoDto;
import edu.utp.nisiadmin.dto.producto.ListaProductoDto;
import edu.utp.nisiadmin.dto.producto.ProductoMapper;
import edu.utp.nisiadmin.dto.producto.RegistroProductoDto;
import edu.utp.nisiadmin.enums.EstadoProducto;
import edu.utp.nisiadmin.model.Producto;
import edu.utp.nisiadmin.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collections;
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

    public Page<ListaProductoDto> obtenerTodosLosProductos(int currentPage, int pageSize) {
        //currentPage acá es con índice 0 (inicia en cero)... el controlador es responsable de restarle uno
        Page<Producto> listado = repositorio.findAll(PageRequest.of(currentPage, pageSize));
        return listado.map(mapper::toLista);
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

    public List<ListaProductoDto> obtenerProductosRelacionados(Long id) {
        Optional<Producto> posibleProducto = repositorio.findById(id);
        if (posibleProducto.isPresent()) {
            String cat = posibleProducto.get().getCategoria();
            //buscar como recomendación los 3 primeros productos de la misma categoría y que no estén agotados
            Page<Producto> relacionados = repositorio.findByCategoriaContainsAndEstadoNot(cat, EstadoProducto.AGOTADO, PageRequest.of(0, 3));
            return relacionados.map(mapper::toLista).toList();
        } else {
            return Collections.emptyList();
        }
    }
}
