package ep2024.u5_w3_d2.exceptions;

import java.util.UUID;

public class NotFoundException extends RuntimeException {
    public NotFoundException(UUID id) {
        super("Element with id " + id + " not found :(");
    }

    public NotFoundException(String message) {
        super(message);
    }
}