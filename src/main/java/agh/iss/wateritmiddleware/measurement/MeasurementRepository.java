package agh.iss.wateritmiddleware.measurement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MeasurementRepository extends JpaRepository<Measurement, Long> {

    @Query("SELECT m FROM Measurement m WHERE m.field.id = :fieldId ORDER BY m.date DESC LIMIT 1")
    Optional<Measurement> findLatestByFieldIdOrderByDateDesc(@Param("fieldId") Long fieldId);

    List<Measurement> findAllByFieldIdOrderByDateDesc(Long fieldId);
}
