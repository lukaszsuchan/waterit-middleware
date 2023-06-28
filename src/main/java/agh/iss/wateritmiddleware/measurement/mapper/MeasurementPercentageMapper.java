package agh.iss.wateritmiddleware.measurement.mapper;

import agh.iss.wateritmiddleware.measurement.Measurement;
import agh.iss.wateritmiddleware.measurement.model.MeasurementDto;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@NoArgsConstructor
@Component
public class MeasurementPercentageMapper {

    public MeasurementDto toPercentageData(Measurement measurement) {
        return MeasurementDto.builder()
                .date(measurement.getDate())
                .lightIntensity(BigDecimal.valueOf(
                        mapDataToPercentage(0f, 1000f, measurement.getLightIntensity().floatValue())
                ))
                .temperature(measurement.getTemperature())
                .rainfall(measurement.getRainfall())
                .humidity(measurement.getHumidity().divide(BigDecimal.valueOf(100)).setScale(2, RoundingMode.HALF_UP))
                .moistureHumidity(measurement.getMoistureHumidity().divide(BigDecimal.valueOf(100)).setScale(2, RoundingMode.HALF_UP))
                .airPurity(BigDecimal.valueOf(mapDataToPercentage(400f, 2000f, measurement.getAirPurity().floatValue())))
                .build();
    }

    private float mapDataToPercentage(float minValue, float maxValue, float value) {
        return (value - minValue) / (maxValue - minValue);
    }
}
