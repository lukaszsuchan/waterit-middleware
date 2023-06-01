package agh.iss.wateritmiddleware.waterrequirement.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SoilType {
    DRY(0, 40.999f),
    HUMID(41, 70.999f),
    WET(71, 100);

    private final float startValue;
    private final float endValue;

}
