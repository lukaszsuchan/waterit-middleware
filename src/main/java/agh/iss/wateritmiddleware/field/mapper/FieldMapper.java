package agh.iss.wateritmiddleware.field.mapper;

import agh.iss.wateritmiddleware.field.Field;
import agh.iss.wateritmiddleware.field.model.FieldDto;
import agh.iss.wateritmiddleware.measurement.mapper.MeasurementMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        uses = {MeasurementMapper.class})
public interface FieldMapper {

    FieldDto toDto(Field field);
}
