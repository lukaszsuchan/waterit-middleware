package agh.iss.wateritmiddleware.measurement.model;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Date;

public record MeasurementDto(
        Date date,
        BigDecimal lightIntensity,
        BigDecimal temperature,
        BigDecimal humidity,
        BigDecimal moistureHumidity,
        BigDecimal rainfall,
        BigDecimal airPurity
) {
}
