package agh.iss.wateritmiddleware.field;

import agh.iss.wateritmiddleware.device.Device;
import agh.iss.wateritmiddleware.device.DeviceService;
import agh.iss.wateritmiddleware.exception.CoreException;
import agh.iss.wateritmiddleware.exception.ErrorCode;
import agh.iss.wateritmiddleware.exception.ErrorSubcode;
import agh.iss.wateritmiddleware.field.mapper.FieldMapper;
import agh.iss.wateritmiddleware.field.model.FieldDto;
import agh.iss.wateritmiddleware.field.model.request.FieldRequest;
import agh.iss.wateritmiddleware.field.model.response.CropTypeResponse;
import agh.iss.wateritmiddleware.measurement.Measurement;
import agh.iss.wateritmiddleware.measurement.MeasurementRepository;
import agh.iss.wateritmiddleware.measurement.MeasurementService;
import agh.iss.wateritmiddleware.user.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FieldService {

    private final FieldRepository fieldRepository;
    private final MeasurementRepository measurementRepository;
    private final FieldMapper fieldMapper;
    private final CurrentUser currentUser;
    private final DeviceService deviceService;

    @Transactional
    public Long addField(FieldRequest request) {
        final var field = Field.builder()
                .name(request.name())
                .area(request.area())
                .actualCropType(request.actualCropType())
                .user(currentUser.getUserInfo())
                .device(deviceService.addDeviceByExternalDeviceId(request.addDeviceRequest()))
                .build();

        final var createdField = fieldRepository.save(field);
        assignMeasurementToField(createdField);

        return createdField.getId();
    }

    private void assignMeasurementToField(Field field) {
        final var measurement = Measurement.builder()
                .field(field)
                .build();

        measurementRepository.save(measurement);
    }

    public CropTypeResponse getAllCropTypes() {
        return CropTypeResponse.builder()
                .cropTypes(Arrays.stream(CropType.values()).toList())
                .build();
    }

    public List<FieldDto> getAllUserFields() {
        return fieldRepository.findAllByUserId(currentUser.getUserInfo().getId()).stream()
                .map(fieldMapper::toDto)
                .toList();
    }

    public FieldDto getFieldById(Long fieldId) {
        return fieldRepository.findById(fieldId)
                .filter(field -> field.getUser().getId() == currentUser.getUserInfo().getId())
                .map(fieldMapper::toDto)
                .orElseThrow(() -> new CoreException(ErrorCode.NOT_FOUND, ErrorSubcode.FIELD_NOT_FOUND));
    }

    public Field getFieldByDevice(Device device) {
        return fieldRepository.findByDevice(device)
                .orElseThrow(() -> new CoreException(ErrorCode.NOT_FOUND, ErrorSubcode.FIELD_NOT_FOUND));
    }
}

