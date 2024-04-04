package cock.and.ball.exampractice.controller;

import cock.and.ball.exampractice.dto.EmployeeDto;
import cock.and.ball.exampractice.entity.Employee;
import cock.and.ball.exampractice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/pageable")
    public ResponseEntity<Page<Employee>> listEmployeesPage(Pageable pageable) {
        return ResponseEntity.ok(employeeService.listAllEmployees(pageable));
    }

    @GetMapping("")
    public ResponseEntity<Page<Employee>> listEmployees(
            @RequestParam(defaultValue = "lastName") String sortBy,
            @RequestParam(defaultValue = "asc") String direction,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(employeeService.listEmployees(sortBy, direction, page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> listEmployeeById(@PathVariable Integer id) {
        return ResponseEntity.ok(employeeService.listEmployeeById(id));
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody EmployeeDto employeeDTO) {
        Employee employee = employeeService.createEmployee(employeeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Integer id, @RequestBody EmployeeDto employeeDTO) {
        Employee updatedEmployee = employeeService.updateEmployee(id, employeeDTO);
        return ResponseEntity.ok(updatedEmployee);
    }

}

