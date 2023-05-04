package agh.iss.wateritmiddleware.field;

import agh.iss.wateritmiddleware.exception.CoreException;
import agh.iss.wateritmiddleware.exception.ErrorCode;
import agh.iss.wateritmiddleware.exception.ErrorSubcode;
import agh.iss.wateritmiddleware.field.mapper.FieldMapper;
import agh.iss.wateritmiddleware.field.mapper.ZoneMapper;
import agh.iss.wateritmiddleware.field.model.FieldDto;
import agh.iss.wateritmiddleware.field.model.ZoneDto;
import agh.iss.wateritmiddleware.field.model.request.FieldRequest;
import agh.iss.wateritmiddleware.field.model.request.ZoneRequest;
import agh.iss.wateritmiddleware.user.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FieldService {

    private final FieldRepository fieldRepository;
    private final ZoneRepository zoneRepository;
    private final FieldMapper fieldMapper;
    private final ZoneMapper zoneMapper;
    private final CurrentUser currentUser;

    public Long addField(FieldRequest request) {
        final var field = Field.builder()
                .name(request.name())
                .area(request.area())
                .actualCropType(request.actualCropType())
                .user(currentUser.getUserInfo())
                .build();

        final var createdField = fieldRepository.save(field);
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

    public Long addZoneAndSplitField(Long fieldId, ZoneRequest request) {
        final var field = fieldRepository.findById(fieldId)
                .orElseThrow(() -> new CoreException(ErrorCode.NOT_FOUND, ErrorSubcode.FIELD_NOT_FOUND));
        double remainingArea = field.getZones().isEmpty() ? field.getArea().doubleValue() :
                field.getZones().stream()
                        .filter(zone -> zone.getAutoSeperated() == Boolean.TRUE)
                        .map(Zone::getArea)
                        .mapToDouble(BigDecimal::doubleValue)
                        .sum();
        if (request.area().doubleValue() >= remainingArea) {
            throw new CoreException(ErrorCode.VALIDATION_ERROR, ErrorSubcode.INCORRECT_ZONE_AREA);
        }
        final var initZone = Zone.builder()
                .name(request.name())
                .area(request.area())
                .actualCropType(request.actualCropType())
                .autoSeperated(false)
                .field(field)
                .build();
        final var restFieldZone = Zone.builder()
                .name(field.getName() + "-zone")
                .area(BigDecimal.valueOf(remainingArea - request.area().doubleValue()))
                .actualCropType(field.getActualCropType())
                .autoSeperated(true)
                .field(field)
                .build();

        Long zoneId = zoneRepository.save(initZone).getId();
        zoneRepository.save(restFieldZone);

        return zoneId;
    }

    public List<ZoneDto> getAllFieldZone(Long fieldId) {
        return zoneRepository.findAllByFieldId(fieldId).stream()
                .map(zoneMapper::toDto)
                .toList();
    }
}

