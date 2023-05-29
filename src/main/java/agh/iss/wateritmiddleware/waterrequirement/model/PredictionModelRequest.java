package agh.iss.wateritmiddleware.waterrequirement.model;

import agh.iss.wateritmiddleware.field.CropType;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record PredictionModelRequest(
        SoilType soilType,
        AirHumidityType airHumidityType,
        WeatherCondition weatherCondition,
        CropType cropType,
        BigDecimal temperature
) {
}
