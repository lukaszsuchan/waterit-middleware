package agh.iss.wateritmiddleware.field.model;

import agh.iss.wateritmiddleware.field.CropType;
import lombok.Builder;
import lombok.NonNull;

import java.math.BigDecimal;

@Builder
public record FieldRequest(
   String name,
   BigDecimal area,
   CropType actualCropType
) {
}
