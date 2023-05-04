package agh.iss.wateritmiddleware.field.model;

import agh.iss.wateritmiddleware.field.CropType;
import agh.iss.wateritmiddleware.measurement.model.MeasurementDto;

import java.math.BigDecimal;
import java.util.List;

public record FieldDto(
    String name,
    BigDecimal area,
    CropType actualcropType,
    MeasurementDto measurement,
    List<ZoneDto> zones
) {
}
