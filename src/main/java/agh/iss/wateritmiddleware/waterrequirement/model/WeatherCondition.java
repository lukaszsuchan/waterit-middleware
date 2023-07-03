package agh.iss.wateritmiddleware.waterrequirement.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.function.Function;

@AllArgsConstructor
public enum WeatherCondition {
    NORMAL(lightIntensity -> new LightIntensity(100.0f, 10000.999f),
            temperature -> new Temperature(15.0f, 25.0f),
            rainfall -> new Rainfall(0)),
    RAINY(lightIntensity -> new LightIntensity(100.0f, 10000.999f),
            temperature -> new Temperature(10.0f, 40.0f),
            rainfall -> new Rainfall(1)),
    SUNNY(lightIntensity -> new LightIntensity(10001.0f, 70000.0f),
            temperature -> new Temperature(20.0f, 60.0f),
            rainfall -> new Rainfall(0)),
    WINDY(lightIntensity -> new LightIntensity(800.0f, 1500.0f),
            temperature -> new Temperature(10.0f, 24.999f),
            rainfall -> new Rainfall(0));

    private final Function<WeatherCondition, LightIntensity> lightIntensitySupplier;
    private final Function<WeatherCondition, Temperature> temperatureSupplier;
    private final Function<WeatherCondition, Rainfall> rainfallSupplier;

    public LightIntensity getLightIntensity() {
        return lightIntensitySupplier.apply(this);
    }

    public Temperature getTemperature() {
        return temperatureSupplier.apply(this);
    }

    public Rainfall getRainfall() {
        return rainfallSupplier.apply(this);
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class LightIntensity {
        private float startValue;
        private float endValue;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Temperature {
        private float startValue;
        private float endValue;
    }

    @AllArgsConstructor
    public static class Rainfall {
        private int isRaining;

        public int isRaining() {
            return this.isRaining;
        }
    }
}
