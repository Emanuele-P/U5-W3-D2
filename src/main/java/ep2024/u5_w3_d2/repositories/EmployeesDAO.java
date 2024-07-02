package ep2024.u5_w3_d2.repositories;

import ep2024.u5_w3_d2.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface EmployeesDAO extends JpaRepository<Employee, UUID> {
    Optional<Employee> findByEmail(String email);

    boolean existsByEmail(String email);

    Optional<Employee> findByUsername(String username);
}
