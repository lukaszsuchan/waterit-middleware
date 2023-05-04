package agh.iss.wateritmiddleware.measurement;

import agh.iss.wateritmiddleware.measurement.model.MeasurementDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/measurement")
@RequiredArgsConstructor
public class MeasurementController {

    private final MeasurementService measurementService;

    @GetMapping("/latest")
    public ResponseEntity<MeasurementDto> getLatestMeasurement() {
        return ResponseEntity.ok(measurementService.getLatestMeasurement());
    }
}
