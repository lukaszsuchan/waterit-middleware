package agh.iss.wateritmiddleware.waterrequirement.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AirHumidityType {
    DESERT(0, 20.999f),
    HUMID(21, 40.999f),
    SEMI_ARID(41, 60.999f),
    SEMI_HUMID(61, 100);

    private final float startValue;
    private final float endValue;
}
