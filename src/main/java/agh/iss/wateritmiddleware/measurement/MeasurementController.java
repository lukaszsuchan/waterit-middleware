package agh.iss.wateritmiddleware.measurement;

import agh.iss.wateritmiddleware.measurement.model.MeasurementDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/measurement")
@RequiredArgsConstructor
public class MeasurementController {

    private final MeasurementService measurementService;

    @GetMapping("/latest")
    public ResponseEntity<MeasurementDto> getLatestMeasurement(@RequestAttribute Long zoneId) {
        return ResponseEntity.ok(measurementService.getLatestMeasurementByZoneId(zoneId));
    }
}
