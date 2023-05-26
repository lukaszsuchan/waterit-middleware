package agh.iss.wateritmiddleware.measurement;

import agh.iss.wateritmiddleware.exception.CoreException;
import agh.iss.wateritmiddleware.exception.ErrorCode;
import agh.iss.wateritmiddleware.exception.ErrorSubcode;
import agh.iss.wateritmiddleware.field.Field;
import agh.iss.wateritmiddleware.measurement.model.MeasurementDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MeasurementService {

    private final MeasurementRepository measurementRepository;
    private final MeasurementMapper measurementMapper;

    public void addMeasurementToField(Field field) {
        final var measurement = Measurement.builder()
                .field(field)
                .build();

        measurementRepository.save(measurement);
    }

    public MeasurementDto getLatestMeasurementByFieldId(Long fieldId) {
        return measurementRepository.findTopByFieldIdOrderByDateDesc(fieldId)
                .map(measurementMapper::toDto)
                .orElseThrow(() -> new CoreException(ErrorCode.NOT_FOUND, ErrorSubcode.MEASUREMENT_NOT_FOUND));
    }
}
