package agh.iss.wateritmiddleware.field;

import agh.iss.wateritmiddleware.exception.CoreException;
import agh.iss.wateritmiddleware.exception.ErrorCode;
import agh.iss.wateritmiddleware.exception.ErrorSubcode;
import agh.iss.wateritmiddleware.field.mapper.FieldMapper;
import agh.iss.wateritmiddleware.field.model.FieldDto;
import agh.iss.wateritmiddleware.field.model.request.FieldRequest;
import agh.iss.wateritmiddleware.measurement.MeasurementService;
import agh.iss.wateritmiddleware.user.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FieldService {

    private final FieldRepository fieldRepository;
    private final MeasurementService measurementService;
    private final FieldMapper fieldMapper;
    private final CurrentUser currentUser;

    public Long addField(FieldRequest request) {
        final var field = Field.builder()
                .name(request.name())
                .area(request.area())
                .actualCropType(request.actualCropType())
                .user(currentUser.getUserInfo())
                .build();

        final var createdField = fieldRepository.save(field);
        measurementService.addMeasurementToField(createdField);

        return createdField.getId();
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
}

