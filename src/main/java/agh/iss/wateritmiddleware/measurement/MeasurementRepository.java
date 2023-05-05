package agh.iss.wateritmiddleware.measurement;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MeasurementRepository extends JpaRepository<Measurement, Long> {

    Optional<Measurement> findTopByZoneIdOrderByDateDesc(Long zoneId);
}
