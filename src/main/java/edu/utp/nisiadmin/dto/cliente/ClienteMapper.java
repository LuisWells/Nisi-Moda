package edu.utp.nisiadmin.dto.cliente;

import edu.utp.nisiadmin.model.Cliente;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ClienteMapper {

    ListaClienteDto toLista(Cliente cliente);

    DetalleClienteDto toDetalle(Cliente cliente);

    Cliente toEntity(RegistroClienteDto registroClienteDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Cliente partialUpdate(RegistroClienteDto registroClienteDto, @MappingTarget Cliente cliente);
}