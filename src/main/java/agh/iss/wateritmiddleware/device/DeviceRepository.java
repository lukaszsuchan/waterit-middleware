package agh.iss.wateritmiddleware.device;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DeviceRepository extends JpaRepository<Device, Long> {

    Optional<Device> findByExternalDeviceId(String externalDeviceId);

    @Query("SELECT d.id FROM Device d WHERE d.externalDeviceId = :externalDeviceId")
    Device findIdByExternalDeviceId(@Param("externalDeviceId") String externalDeviceId);
}
