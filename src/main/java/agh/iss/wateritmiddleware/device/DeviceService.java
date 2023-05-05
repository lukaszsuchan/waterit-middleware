package agh.iss.wateritmiddleware.device;

import agh.iss.wateritmiddleware.device.model.AddDeviceRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeviceService {

    private final DeviceRepository deviceRepository;
    private final DeviceMapper deviceMapper;

    public Long addDeviceByExternalDeviceId(AddDeviceRequest request) {
        final var device = Device.builder()
                .externalDeviceId(request.externalDeviceId())
                .active(true)
                .build();

        return deviceRepository.save(device).getId();
    }
}
