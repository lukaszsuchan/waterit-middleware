package agh.iss.wateritmiddleware.field.model.response;

import agh.iss.wateritmiddleware.field.CropType;
import lombok.Builder;

import java.util.List;

@Builder
public record CropTypeResponse(
        List<CropType> cropTypes
) {
}
