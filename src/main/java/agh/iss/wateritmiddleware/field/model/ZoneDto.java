package agh.iss.wateritmiddleware.field.model;

import agh.iss.wateritmiddleware.device.model.DeviceDto;
import agh.iss.wateritmiddleware.field.CropType;

import java.math.BigDecimal;

public record ZoneDto(
        Long id,
        String name,
        BigDecimal area,
        CropType cropType,
        DeviceDto device
) {
}
