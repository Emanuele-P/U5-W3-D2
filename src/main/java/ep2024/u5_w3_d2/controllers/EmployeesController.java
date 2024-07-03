package ep2024.u5_w3_d2.controllers;

import ep2024.u5_w3_d2.entities.Employee;
import ep2024.u5_w3_d2.exceptions.BadRequestException;
import ep2024.u5_w3_d2.payloads.EmployeeDTO;
import ep2024.u5_w3_d2.services.EmployeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/employees")
public class EmployeesController {
    @Autowired
    private EmployeesService employeesService;

    //1. GET all http://localhost:3001/employees
    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<Employee> getAllEmployees(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size,
                                          @RequestParam(defaultValue = "id") String sortBy) {
        return employeesService.getEmployees(page, size, sortBy);
    }

    //2. POST http://localhost:3001/employees (+body) look auth

    //3. GET one http://localhost:3001/employees/{id}
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Employee findEmployeeById(@PathVariable UUID id) {
        return employeesService.findById(id);
    }

    @GetMapping("/me")
    public Employee getProfile(@AuthenticationPrincipal Employee currentAuthenticatedUser){
        return currentAuthenticatedUser;
    }

    //4. PUT http://localhost:3001/employees/{id} (+body)
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Employee findEmployeeByIdAndUpdate(@PathVariable UUID id, @RequestBody @Validated EmployeeDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            System.out.println(validationResult.getAllErrors());
            throw new BadRequestException(validationResult.getAllErrors());
        }
        return employeesService.findByIdAndUpdate(id, body);
    }

    @PutMapping("/me")
    public Employee updateProfile(@AuthenticationPrincipal Employee currentAuthenticatedUser, @RequestBody @Validated EmployeeDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            System.out.println(validationResult.getAllErrors());
            throw new BadRequestException(validationResult.getAllErrors());
        }
        return employeesService.findByIdAndUpdate(currentAuthenticatedUser.getId(), body);
    }

    //5. DELETE http://localhost:3001/employees/{id}
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findEmployeeByIdAndDelete(@PathVariable UUID id) {
        employeesService.findByIdAndDelete(id);
    }

    //6. UPLOAD AVATAR http://localhost:3001/employees/{id}/avatar (+body)
    @PostMapping("/{id}/avatar")
    public Employee uploadAvatar(@PathVariable UUID id, @RequestParam("avatar") MultipartFile image) throws IOException {
        String avatarURL = employeesService.uploadAvatar(image);
        return employeesService.updateAvatar(id, avatarURL);
    }
}
