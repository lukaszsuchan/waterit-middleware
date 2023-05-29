package agh.iss.wateritmiddleware.measurement;

import agh.iss.wateritmiddleware.measurement.model.MeasurementDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/measurement")
@RequiredArgsConstructor
public class MeasurementController {

    private final MeasurementService measurementService;

    @PostMapping()
    public ResponseEntity<Void> sendMeasurementFromDevice(@RequestBody MeasurementDto measurementDto) {
        measurementService.addMeasurement(measurementDto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/latest")
    public ResponseEntity<MeasurementDto> getLatestMeasurement(@RequestParam Long fieldId) {
        return ResponseEntity.ok(measurementService.getLatestMeasurementByFieldId(fieldId));
    }

    @GetMapping("/history")
    public ResponseEntity<List<MeasurementDto>> getAllMeasurements(@RequestParam Long fieldId) {
        return ResponseEntity.ok(measurementService.getAllMeasurementsByFieldId(fieldId));
    }
}
