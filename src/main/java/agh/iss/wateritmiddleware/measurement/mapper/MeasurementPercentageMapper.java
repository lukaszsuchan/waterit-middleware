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
                .lightIntensity(mapDataToPercentage(BigDecimal.ZERO, BigDecimal.valueOf(1000), measurement.getLightIntensity()))
                .temperature(measurement.getTemperature())
                .rainfall(measurement.getRainfall())
                .humidity(measurement.getHumidity().divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP))
                .moistureHumidity(measurement.getMoistureHumidity().divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP))
                .airPurity(mapDataToPercentage(BigDecimal.valueOf(50), BigDecimal.valueOf(1000), measurement.getAirPurity()))
                .build();
    }

    private BigDecimal mapDataToPercentage(BigDecimal minValue, BigDecimal maxValue, BigDecimal value) {
        return (value.add(minValue.negate())).divide(maxValue.add(minValue.negate())).multiply(BigDecimal.valueOf(100)).setScale(2, RoundingMode.HALF_UP);
    }
}
