package ep2024.u5_w3_d2.repositories;

import ep2024.u5_w3_d2.entities.Device;
import ep2024.u5_w3_d2.enums.DeviceAvailability;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DevicesDAO extends JpaRepository<Device, UUID> {
    List<Device> findAllByAvailability(DeviceAvailability availability);
}
