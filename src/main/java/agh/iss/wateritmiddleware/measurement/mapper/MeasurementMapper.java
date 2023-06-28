package agh.iss.wateritmiddleware.measurement.mapper;

import agh.iss.wateritmiddleware.measurement.Measurement;
import agh.iss.wateritmiddleware.measurement.model.MeasurementDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MeasurementMapper {

    MeasurementDto toDto(Measurement measurement);

    Measurement toJpa(MeasurementDto measurementDto);
}
