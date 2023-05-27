package agh.iss.wateritmiddleware.device;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DeviceRepository extends JpaRepository<Device, Long> {

    @Query("SELECT d.id FROM Device d WHERE d.externalDeviceId = :externalDeviceId")
    Long findIdByExternalDeviceId(@Param("externalDeviceId") String externalDeviceId);
}
