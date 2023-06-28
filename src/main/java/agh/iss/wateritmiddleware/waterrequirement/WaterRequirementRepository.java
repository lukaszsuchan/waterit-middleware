package agh.iss.wateritmiddleware.waterrequirement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WaterRequirementRepository extends JpaRepository<WaterRequirement, Long> {

    Optional<WaterRequirement> findFirstByFieldIdOrderByDateDesc(Long fieldId);
}
