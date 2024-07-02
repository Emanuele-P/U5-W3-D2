package ep2024.u5_w3_d2.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

public record EmployeeDTO(
        @NotBlank(message = "Username must not be empty!")
        @Size(min = 5, max = 20, message = "Username must be between 5 and 20 characters.")
        String username,
        @NotBlank(message = "First name must not be empty!")
        @Size(min = 3, max = 30, message = "First name must be between 5 and 20 characters.")
        String firstName,
        @NotBlank(message = "Last name must not be empty!")
        @Size(min = 3, max = 30, message = "Last name must be between 5 and 20 characters.")
        String lastName,
        @NotBlank(message = "Email must not be empty!")
        @Email(message = "Email must be a valid email address.")
        String email,
        @NotBlank(message = "{Password must not be empty!")
        @Size(min = 5, message = "Password must be at least 5 characters.")
        String password,
        @URL(message = "Avatar URL must be a valid URL address!")
        String avatarUrl
) {
}
