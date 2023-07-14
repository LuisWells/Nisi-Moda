package edu.utp.nisiadmin.service;

import edu.utp.nisiadmin.dto.compra.CompraMapper;
import edu.utp.nisiadmin.dto.compra.DetalleCompraDto;
import edu.utp.nisiadmin.dto.compra.ListaCompraDto;
import edu.utp.nisiadmin.dto.compra.RegistroCompraDto;
import edu.utp.nisiadmin.dto.itemcompra.ItemCompraMapper;
import edu.utp.nisiadmin.model.Compra;
import edu.utp.nisiadmin.model.ItemCompra;
import edu.utp.nisiadmin.repository.CompraRepository;
import edu.utp.nisiadmin.repository.ItemCompraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompraService {
    private final CompraRepository repositorio;
    private final ItemCompraRepository itemRepositorio;
    private final CompraMapper mapper;
    private final ItemCompraMapper itemMapper;

    public DetalleCompraDto obtenerCompra(Long id) {
        Optional<Compra> posibleCompra = repositorio.findById(id);
        return posibleCompra.map(mapper::toDetalle).orElse(null);
    }

    public List<ListaCompraDto> obtenerTodasLasCompras() {
        List<Compra> listado = repositorio.findAll();
        return listado.stream().map(mapper::toLista).collect(Collectors.toList());
    }

    public Long crearCompra(RegistroCompraDto dto) {
        List<ItemCompra> items = new ArrayList<>();
        try {
            Compra compra = mapper.toEntity(dto);
            compra.setItems(items);
            Compra compraGuardada = repositorio.save(compra);
            return compraGuardada.getId();
        } catch (Exception e) {
            return -1L; //retorna negativo si hubo un error
        }
    }


    public boolean borrarCompra(Long id) {
        Optional<Compra> posibleCompra = repositorio.findById(id);
        if (posibleCompra.isPresent()) {
            repositorio.deleteById(id);
            return true;
        }
        return false;
    }
}
