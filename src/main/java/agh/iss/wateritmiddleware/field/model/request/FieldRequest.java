package agh.iss.wateritmiddleware.field.model.request;

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
