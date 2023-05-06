package agh.iss.wateritmiddleware.field;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ZoneRepository extends JpaRepository<Zone, Long> {
    List<Zone> findAllByFieldId(Long fieldId);

    Optional<List<Zone>> findByAutoSeperatedIsTrue();

    @Modifying
    @Query("delete from Zone z where z.id in ?1")
    void deleteByIdIn(List<Long> ids);
}
