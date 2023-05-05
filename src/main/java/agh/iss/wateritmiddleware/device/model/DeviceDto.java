package agh.iss.wateritmiddleware.device.model;

import lombok.Builder;

@Builder
public record DeviceDto(
        Long id,
        String externalDeviceId,
        boolean active
) {
}
