package agh.iss.wateritmiddleware.waterrequirement;

import agh.iss.wateritmiddleware.measurement.model.MeasurementDto;
import agh.iss.wateritmiddleware.waterrequirement.model.WeatherCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class WeatherConditionService {


    public WeatherCondition getWeatherConditionByMeasurement(MeasurementDto measurementDto) {
        BigDecimal lightIntensity = measurementDto.lightIntensity();
        BigDecimal temperature = measurementDto.temperature();
        BigDecimal rainfall = measurementDto.rainfall();
        for (WeatherCondition weatherCondition : WeatherCondition.values()) {
            WeatherCondition.LightIntensity lightIntensityFitter = weatherCondition.getLightIntensity();
            boolean isLightIntensityFit = lightIntensity.compareTo(BigDecimal.valueOf(lightIntensityFitter.getStartValue())) >= 0
                    && lightIntensity.compareTo(BigDecimal.valueOf(lightIntensityFitter.getEndValue())) <= 0;

            WeatherCondition.Temperature temperatureFitter = weatherCondition.getTemperature();
            boolean isTemperatureFit = temperature.compareTo(BigDecimal.valueOf(temperatureFitter.getStartValue())) >= 0
                    && temperature.compareTo(BigDecimal.valueOf(temperatureFitter.getEndValue())) <= 0;

            WeatherCondition.Rainfall rainfallFitter = weatherCondition.getRainfall();
            boolean isRainfallFit = rainfall.compareTo(BigDecimal.valueOf(rainfallFitter.getStartValue())) >= 0
                    && rainfall.compareTo(BigDecimal.valueOf(rainfallFitter.getEndValue())) <= 0;

            if (isLightIntensityFit && isTemperatureFit && isRainfallFit) {
                return weatherCondition;
            }
        }

        return WeatherCondition.NORMAL;
    }
}
