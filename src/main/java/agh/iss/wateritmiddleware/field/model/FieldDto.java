package agh.iss.wateritmiddleware.field.model;

import agh.iss.wateritmiddleware.field.CropType;

import java.math.BigDecimal;
import java.util.List;

public record FieldDto(
        String name,
        BigDecimal area,
        CropType actualcropType,
        List<ZoneDto> zones
) {
}
