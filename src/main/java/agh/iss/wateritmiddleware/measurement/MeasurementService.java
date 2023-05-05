package agh.iss.wateritmiddleware.measurement;

import agh.iss.wateritmiddleware.exception.CoreException;
import agh.iss.wateritmiddleware.exception.ErrorCode;
import agh.iss.wateritmiddleware.exception.ErrorSubcode;
import agh.iss.wateritmiddleware.field.Zone;
import agh.iss.wateritmiddleware.measurement.model.MeasurementDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MeasurementService {

    private final MeasurementRepository measurementRepository;
    private final MeasurementMapper measurementMapper;

    public void addMeasurementToZone(Zone zone) {
        final var measurement = Measurement.builder()
                .zone(zone)
                .measurementType(MeasurementType.ZONE_MEASUREMENT)
                .build();

        measurementRepository.save(measurement);
    }

    public MeasurementDto getLatestMeasurementByZoneId(Long zoneId) {
        return measurementRepository.findTopByZoneIdOrderByDateDesc(zoneId)
                .map(measurementMapper::toDto)
                .orElseThrow(() -> new CoreException(ErrorCode.NOT_FOUND, ErrorSubcode.MEASUREMENT_NOT_FOUND));
    }
}
