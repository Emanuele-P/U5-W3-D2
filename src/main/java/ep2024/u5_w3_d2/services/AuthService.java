package ep2024.u5_w3_d2.services;

import ep2024.u5_w3_d2.entities.Employee;
import ep2024.u5_w3_d2.exceptions.UnauthorizedException;
import ep2024.u5_w3_d2.payloads.EmployeeLoginDTO;
import ep2024.u5_w3_d2.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private EmployeesService employeesService;

    @Autowired
    private JWTTools jwtTools;

    public String authenticateUserAndGenerateToken(EmployeeLoginDTO payload) {

        Employee employee = this.employeesService.findByEmail(payload.email());
        if (employee.getPassword().equals(payload.password())) {
            return jwtTools.createToken(employee);
        } else {
            throw new UnauthorizedException("Your credentials are incorrect!");
        }
    }
}