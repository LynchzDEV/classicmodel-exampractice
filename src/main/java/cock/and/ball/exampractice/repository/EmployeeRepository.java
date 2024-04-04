package cock.and.ball.exampractice.repository;

import cock.and.ball.exampractice.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}