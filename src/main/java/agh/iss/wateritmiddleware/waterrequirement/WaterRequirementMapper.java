package agh.iss.wateritmiddleware.waterrequirement;

import agh.iss.wateritmiddleware.waterrequirement.model.WaterRequirementDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WaterRequirementMapper {

    WaterRequirementDto toDto(WaterRequirement waterRequirement);
}
