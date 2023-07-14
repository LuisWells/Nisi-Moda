package edu.utp.nisiadmin.dto.empleado;

import edu.utp.nisiadmin.model.Empleado;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface EmpleadoMapper {

    ListaEmpleadoDto toLista(Empleado empleado);


    DetalleEmpleadoDto toDetalle(Empleado empleado);

    Empleado toEntity(RegistroEmpleadoDto registroEmpleadoDto);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Empleado partialUpdate(RegistroEmpleadoDto registroEmpleadoDto, @MappingTarget Empleado empleado);
}