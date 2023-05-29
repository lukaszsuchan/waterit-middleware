package agh.iss.wateritmiddleware.device;

import agh.iss.wateritmiddleware.device.model.AddDeviceRequest;
import agh.iss.wateritmiddleware.exception.CoreException;
import agh.iss.wateritmiddleware.exception.ErrorCode;
import agh.iss.wateritmiddleware.exception.ErrorSubcode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeviceService {

    private final DeviceRepository deviceRepository;
    private final DeviceMapper deviceMapper;

    public Long addDeviceByExternalDeviceId(AddDeviceRequest request) {
        Device device = deviceRepository.findByExternalDeviceId(request.externalDeviceId())
                .orElseThrow(() -> new CoreException(ErrorCode.NOT_FOUND, ErrorSubcode.DEVICE_NOT_FOUND));

        if(!device.isActive()) {
            device.setActive(true);
        } else {
            throw new CoreException(ErrorCode.VALIDATION_ERROR, ErrorSubcode.DEVICE_IS_ALREADY_IN_USE);
        }

        return device.getId();
    }

    public Long getDeviceId(String externalDeviceId) {
        return deviceRepository.findIdByExternalDeviceId(externalDeviceId);
    }
}
