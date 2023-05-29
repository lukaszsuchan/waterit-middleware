package agh.iss.wateritmiddleware.waterrequirement;

import agh.iss.wateritmiddleware.waterrequirement.model.WaterRequirementDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/water-requirement")
public class WaterRequirementController {

    private final WaterRequirementService waterRequirementService;

    @GetMapping()
    ResponseEntity<WaterRequirementDto> getWaterRequirement(@RequestParam Long fieldId) {
        return ResponseEntity.ok(waterRequirementService.getLatestWaterRequirement(fieldId));
    }

}
