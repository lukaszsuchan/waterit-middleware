package agh.iss.wateritmiddleware.device;

import agh.iss.wateritmiddleware.device.model.DeviceDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DeviceMapper {

    DeviceDto toDto(Device device);
}
