package ep2024.u5_w3_d2.exceptions;

public class NoAssignedDevicesException extends RuntimeException {
    public NoAssignedDevicesException() {
        super("No devices are currently assigned.");
    }
}
