package ep2024.u5_w3_d2.exceptions;

import lombok.Getter;

import java.util.UUID;

@Getter
public class DeviceAlreadyAssignedException extends RuntimeException {
    private UUID employeeId;

    public DeviceAlreadyAssignedException(UUID employeeId) {
        super("This device is already assigned to employee " + employeeId);
        this.employeeId = employeeId;
    }
}
