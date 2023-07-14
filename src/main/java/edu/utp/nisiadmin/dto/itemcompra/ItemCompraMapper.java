package edu.utp.nisiadmin.dto.itemcompra;

import edu.utp.nisiadmin.model.ItemCompra;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ItemCompraMapper {
    @Mapping(source = "productoId", target = "producto.id")
    ItemCompra toEntity(RegistroItemCompraDto registroItemCompraDto);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "productoId", target = "producto.id")
    ItemCompra partialUpdate(RegistroItemCompraDto registroItemCompraDto, @MappingTarget ItemCompra itemCompra);

    RegistroItemCompraDto toDto(ItemCompra itemCompra);
}