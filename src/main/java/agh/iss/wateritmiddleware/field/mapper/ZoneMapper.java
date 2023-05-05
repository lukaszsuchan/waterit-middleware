package agh.iss.wateritmiddleware.field.mapper;

import agh.iss.wateritmiddleware.device.DeviceMapper;
import agh.iss.wateritmiddleware.field.Zone;
import agh.iss.wateritmiddleware.field.model.ZoneDto;
import agh.iss.wateritmiddleware.measurement.MeasurementMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {MeasurementMapper.class, DeviceMapper.class})
public interface ZoneMapper {

    ZoneDto toDto(Zone zone);
}
