package agh.iss.wateritmiddleware.waterrequirement;

import agh.iss.wateritmiddleware.waterrequirement.model.WaterRequirementDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WaterRequirementMapper {

    @Mapping(source = "value", target = "waterRequireAmount")
    WaterRequirementDto toDto(WaterRequirement waterRequirement);
}
