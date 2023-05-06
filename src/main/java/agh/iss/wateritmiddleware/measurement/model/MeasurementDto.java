package agh.iss.wateritmiddleware.measurement.model;

import agh.iss.wateritmiddleware.measurement.MeasurementType;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Date;

public record MeasurementDto(
        @NotNull Date date,
        BigDecimal lightIntensity,
        BigDecimal temperature,
        BigDecimal humidity,
        BigDecimal moistureHumidity,
        BigDecimal rainfall,
        BigDecimal airPurity,
        MeasurementType measurementType
) {
}
