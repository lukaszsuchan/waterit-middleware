package agh.iss.wateritmiddleware.field;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ZoneRepository extends JpaRepository<Zone, Long> {
    List<Zone> findAllByFieldId(Long fieldId);
}
