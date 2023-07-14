package edu.utp.nisiadmin.dto.compra;

import edu.utp.nisiadmin.dto.itemcompra.ItemCompraMapper;
import edu.utp.nisiadmin.model.Compra;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {ItemCompraMapper.class})
public interface CompraMapper {

    ListaCompraDto toLista(Compra compra);

    DetalleCompraDto toDetalle(Compra compra);

    Compra toEntity(RegistroCompraDto registroCompraDto);

}