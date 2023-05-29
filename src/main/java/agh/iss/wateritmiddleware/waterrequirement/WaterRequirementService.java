package agh.iss.wateritmiddleware.waterrequirement;

import agh.iss.wateritmiddleware.exception.CoreException;
import agh.iss.wateritmiddleware.exception.ErrorCode;
import agh.iss.wateritmiddleware.exception.ErrorSubcode;
import agh.iss.wateritmiddleware.field.Field;
import agh.iss.wateritmiddleware.waterrequirement.model.PredictionModelRequest;
import agh.iss.wateritmiddleware.waterrequirement.model.WaterRequirementDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class WaterRequirementService {

    private final WaterRequirementRepository waterRequirementRepository;
    private final PredictionModelConnector predictionModelConnector;
    private final WaterRequirementMapper waterRequirementMapper;

    public void updateWaterRequirement(Field field, PredictionModelRequest predictionModelRequest) {

        final var response = predictionModelConnector.getPrediction(predictionModelRequest);

        WaterRequirement waterRequirement = WaterRequirement.builder()
                .date(new Date())
                .value(response.prediction())
                .field(field)
                .build();

        waterRequirementRepository.save(waterRequirement);

    }

    public WaterRequirementDto getLatestWaterRequirement(Long fieldId) {
        return waterRequirementRepository.findTopByFieldIdOrderByDateDesc(fieldId)
                .map(waterRequirementMapper::toDto)
                .orElseThrow(() -> new CoreException(ErrorCode.NOT_FOUND, ErrorSubcode.WATER_REQUIREMENT_NOT_FOUND));
    }
}
