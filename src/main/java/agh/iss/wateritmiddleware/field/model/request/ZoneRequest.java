package agh.iss.wateritmiddleware.field.model.request;

import agh.iss.wateritmiddleware.device.model.AddDeviceRequest;
import agh.iss.wateritmiddleware.field.CropType;

import java.math.BigDecimal;

public record ZoneRequest(
        String name,
        BigDecimal area,
        CropType actualCropType,
        AddDeviceRequest addDeviceRequest
) {
}
