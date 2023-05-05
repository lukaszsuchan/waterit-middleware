package agh.iss.wateritmiddleware.field;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FieldRepository extends JpaRepository<Field, Long> {

    List<Field> findAllByUserId(Long userId);
}
