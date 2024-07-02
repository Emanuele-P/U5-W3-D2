package ep2024.u5_w3_d2.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ep2024.u5_w3_d2.enums.DeviceAvailability;
import ep2024.u5_w3_d2.enums.DeviceType;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "devices")
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = "employee")
public class Device {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DeviceType type;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DeviceAvailability availability;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    @JsonIgnore
    private Employee employee;

    public Device(DeviceType type, DeviceAvailability availability) {
        this.type = type;
        this.availability = availability;
    }
}
