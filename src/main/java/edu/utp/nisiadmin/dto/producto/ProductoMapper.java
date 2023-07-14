package edu.utp.nisiadmin.dto.producto;

import edu.utp.nisiadmin.model.Producto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductoMapper {

    ListaProductoDto toLista(Producto producto);

    DetalleProductoDto toDetalle(Producto producto);

    Producto toEntity(RegistroProductoDto registroProductoDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Producto partialUpdate(RegistroProductoDto registroProductoDto, @MappingTarget Producto producto);
}