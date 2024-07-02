package ep2024.u5_w3_d2.controllers;

import ep2024.u5_w3_d2.exceptions.BadRequestException;
import ep2024.u5_w3_d2.payloads.EmployeeDTO;
import ep2024.u5_w3_d2.payloads.EmployeeLoginDTO;
import ep2024.u5_w3_d2.payloads.EmployeeLoginResponseDTO;
import ep2024.u5_w3_d2.payloads.EmployeeResponseDTO;
import ep2024.u5_w3_d2.services.AuthService;
import ep2024.u5_w3_d2.services.EmployeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private EmployeesService employeesService;

    @PostMapping("/login")
    public EmployeeLoginResponseDTO login(@RequestBody EmployeeLoginDTO payload) {
        return new EmployeeLoginResponseDTO(authService.authenticateUserAndGenerateToken(payload));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeResponseDTO saveUser(@RequestBody @Validated EmployeeDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            System.out.println(validationResult.getAllErrors());
            throw new BadRequestException(validationResult.getAllErrors());
        }
        return new EmployeeResponseDTO(this.employeesService.save(body).getId());
    }
}
