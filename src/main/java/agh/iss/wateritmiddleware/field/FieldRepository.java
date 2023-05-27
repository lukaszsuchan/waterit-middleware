package agh.iss.wateritmiddleware.field;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

public interface FieldRepository extends JpaRepository<Field, Long> {

    List<Field> findAllByUserId(Long userId);

    Optional<Field> findByDeviceId(Long deviceId);
}
