package edu.utp.nisiadmin.model;

import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CompraMapper {
    Compra toEntity(ListaCompraDto listaCompraDto);

    ListaCompraDto toDto(Compra compra);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Compra partialUpdate(ListaCompraDto listaCompraDto, @MappingTarget Compra compra);

    Compra toEntity(DetalleCompraDto detalleCompraDto);

    DetalleCompraDto toDto1(Compra compra);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Compra partialUpdate(DetalleCompraDto detalleCompraDto, @MappingTarget Compra compra);
}