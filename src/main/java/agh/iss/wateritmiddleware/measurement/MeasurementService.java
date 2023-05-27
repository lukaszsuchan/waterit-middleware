package agh.iss.wateritmiddleware.measurement;

import agh.iss.wateritmiddleware.device.DeviceService;
import agh.iss.wateritmiddleware.device.model.DeviceDto;
import agh.iss.wateritmiddleware.exception.CoreException;
import agh.iss.wateritmiddleware.exception.ErrorCode;
import agh.iss.wateritmiddleware.exception.ErrorSubcode;
import agh.iss.wateritmiddleware.field.Field;
import agh.iss.wateritmiddleware.field.FieldService;
import agh.iss.wateritmiddleware.measurement.model.MeasurementDto;
import agh.iss.wateritmiddleware.user.CurrentUser;
import agh.iss.wateritmiddleware.user.Role;
import agh.iss.wateritmiddleware.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MeasurementService {

    private final MeasurementRepository measurementRepository;
    private final MeasurementMapper measurementMapper;
    private final CurrentUser currentUser;
    private final DeviceService deviceService;
    private final FieldService fieldService;

    public void addMeasurement(MeasurementDto measurementDto) {
        if(currentUser.getUserInfo().getRole() != Role.DEVICE) {
            throw new CoreException(ErrorCode.VALIDATION_ERROR, ErrorSubcode.NOT_DEVICE);
        }

        Long deviceId = deviceService.getDeviceId(currentUser.getUserInfo().getUsername());
        Field field = fieldService.getFieldByDeviceId(deviceId);

        Measurement measurement = measurementMapper.toJpa(measurementDto);
        measurement.setField(field);

        measurementRepository.save(measurement);
    }

    public MeasurementDto getLatestMeasurementByFieldId(Long fieldId) {
        return measurementRepository.findTopByFieldIdOrderByDateDesc(fieldId)
                .map(measurementMapper::toDto)
                .orElseThrow(() -> new CoreException(ErrorCode.NOT_FOUND, ErrorSubcode.MEASUREMENT_NOT_FOUND));
    }

    public List<MeasurementDto> getAllMeasurementsByFieldId(Long fieldId) {
        return measurementRepository.findAllByFieldIdOrderByDateDesc(fieldId).stream()
                .map(measurementMapper::toDto)
                .toList();
    }
}
