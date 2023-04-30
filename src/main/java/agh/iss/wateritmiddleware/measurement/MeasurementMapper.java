package agh.iss.wateritmiddleware.measurement;

import agh.iss.wateritmiddleware.measurement.model.MeasurementDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MeasurementMapper {

    MeasurementDto toDto(Measurement measurement);
    Measurement toJpa(MeasurementDto measurementDto);
}
