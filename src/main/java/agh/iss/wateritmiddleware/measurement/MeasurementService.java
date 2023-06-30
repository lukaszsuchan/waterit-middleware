package agh.iss.wateritmiddleware.measurement;

import agh.iss.wateritmiddleware.device.Device;
import agh.iss.wateritmiddleware.device.DeviceService;
import agh.iss.wateritmiddleware.exception.CoreException;
import agh.iss.wateritmiddleware.exception.ErrorCode;
import agh.iss.wateritmiddleware.exception.ErrorSubcode;
import agh.iss.wateritmiddleware.field.Field;
import agh.iss.wateritmiddleware.field.FieldService;
import agh.iss.wateritmiddleware.measurement.mapper.MeasurementMapper;
import agh.iss.wateritmiddleware.measurement.mapper.MeasurementPercentageMapper;
import agh.iss.wateritmiddleware.measurement.model.MeasurementDto;
import agh.iss.wateritmiddleware.user.CurrentUser;
import agh.iss.wateritmiddleware.user.Role;
import agh.iss.wateritmiddleware.waterrequirement.WaterRequirementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MeasurementService {

    private final MeasurementRepository measurementRepository;
    private final MeasurementMapper measurementMapper;
    private final MeasurementPercentageMapper measurementPercentageMapper;
    private final CurrentUser currentUser;
    private final DeviceService deviceService;
    private final FieldService fieldService;
    private final WaterRequirementService waterRequirementService;

    @Transactional
    public void addMeasurement(MeasurementDto measurementDto) {
        if(currentUser.getUserInfo().getRole() != Role.DEVICE) {
            throw new CoreException(ErrorCode.VALIDATION_ERROR, ErrorSubcode.NOT_DEVICE);
        }

        Device device = deviceService.getDevice(currentUser.getUserInfo().getUsername());
        Field field = fieldService.getFieldByDevice(device);

        waterRequirementService.updateWaterRequirement(field, measurementDto);

        Measurement measurement = measurementMapper.toJpa(measurementDto);
        measurement.setField(field);
        measurement.setDate(new Date());

        measurementRepository.save(measurement);

    }

    public MeasurementDto getLatestMeasurementByFieldId(Long fieldId) {
        return measurementRepository.findLatestByFieldIdOrderByDateDesc(fieldId)
                .map(measurementPercentageMapper::toPercentageData)
                .orElseThrow(() -> new CoreException(ErrorCode.NOT_FOUND, ErrorSubcode.MEASUREMENT_NOT_FOUND));
    }

    public List<MeasurementDto> getAllMeasurementsByFieldId(Long fieldId) {
        return measurementRepository.findAllByFieldIdOrderByDateDesc(fieldId).stream()
                .map(measurementMapper::toDto)
                .toList();
    }
}
