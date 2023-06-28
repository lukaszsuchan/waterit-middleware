package agh.iss.wateritmiddleware.waterrequirement;

import agh.iss.wateritmiddleware.exception.CoreException;
import agh.iss.wateritmiddleware.exception.ErrorCode;
import agh.iss.wateritmiddleware.exception.ErrorSubcode;
import agh.iss.wateritmiddleware.field.Field;
import agh.iss.wateritmiddleware.measurement.model.MeasurementDto;
import agh.iss.wateritmiddleware.waterrequirement.model.AirHumidityType;
import agh.iss.wateritmiddleware.waterrequirement.model.PredictionModelRequest;
import agh.iss.wateritmiddleware.waterrequirement.model.SoilType;
import agh.iss.wateritmiddleware.waterrequirement.model.WaterRequirementDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class WaterRequirementService {

    private final WaterRequirementRepository waterRequirementRepository;
    private final PredictionModelConnector predictionModelConnector;
    private final WaterRequirementMapper waterRequirementMapper;
    private final WeatherConditionService weatherConditionService;

    public void updateWaterRequirement(Field field, MeasurementDto measurementDto) {

        final var predictionModelRequest = PredictionModelRequest.builder()
                .soilType(getSoilTypeByMoistureHumidity(measurementDto.moistureHumidity()))
                .airHumidityType(getAirHumidityTypeByHumidity(measurementDto.humidity()))
                .weatherCondition(weatherConditionService.getWeatherConditionByMeasurement(measurementDto))
                .cropType(field.getActualCropType())
                .temperature(measurementDto.temperature())
                .build();


        final var response = predictionModelConnector.getPrediction(predictionModelRequest);

        WaterRequirement waterRequirement = WaterRequirement.builder()
                .date(new Date())
                .value(response.prediction())
                .field(field)
                .build();

        waterRequirementRepository.save(waterRequirement);
    }

    public WaterRequirementDto getLatestWaterRequirement(Long fieldId) {
        return waterRequirementRepository.findFirstByFieldIdOrderByDateDesc(fieldId)
                .map(waterRequirementMapper::toDto)
                .orElseThrow(() -> new CoreException(ErrorCode.NOT_FOUND, ErrorSubcode.WATER_REQUIREMENT_NOT_FOUND));
    }

    private SoilType getSoilTypeByMoistureHumidity(BigDecimal moistureHumidity) {
        for (SoilType soilType : SoilType.values()) {
            if (moistureHumidity.compareTo(BigDecimal.valueOf(soilType.getStartValue())) >= 0
                    && moistureHumidity.compareTo(BigDecimal.valueOf(soilType.getEndValue())) <= 0) {
                return soilType;
            }
        }
        return SoilType.HUMID;
    }

    private AirHumidityType getAirHumidityTypeByHumidity(BigDecimal humidity) {
        for (AirHumidityType airHumidityType : AirHumidityType.values()) {
            if (humidity.compareTo(BigDecimal.valueOf(airHumidityType.getStartValue())) >= 0
                    && humidity.compareTo(BigDecimal.valueOf(airHumidityType.getEndValue())) <= 0) {
                return airHumidityType;
            }
        }
        return AirHumidityType.HUMID;
    }
}
