package agh.iss.wateritmiddleware.user.model;

import agh.iss.wateritmiddleware.field.model.FieldDto;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record UserDto(
        @NotNull String username,
        List<FieldDto> fileds
) {
}
