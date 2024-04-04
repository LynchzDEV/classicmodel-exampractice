package cock.and.ball.exampractice.service;

import cock.and.ball.exampractice.dto.EmployeeDto;
import cock.and.ball.exampractice.entity.Employee;
import cock.and.ball.exampractice.entity.Office;
import cock.and.ball.exampractice.repository.EmployeeRepository;
import cock.and.ball.exampractice.repository.OfficeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final OfficeRepository officeRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, OfficeRepository officeRepository) {
        this.employeeRepository = employeeRepository;
        this.officeRepository = officeRepository;

    }

    public Page<Employee> listAllEmployees(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    public Page<Employee> listEmployees(String sortBy, String direction, int page, int size) {
        Sort.Direction sortDirection = Sort.Direction.fromString(direction.toUpperCase());
        String sortProperty = sortBy.isEmpty() ? "lastName" : sortBy;
        Sort sort = Sort.by(sortDirection, sortProperty);

        Pageable pageable = PageRequest.of(page, size, sort);

        return employeeRepository.findAll(pageable);
    }

    public Employee listEmployeeById(Integer id) {
        return employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found") {
        });
    }

    @Transactional
    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Transactional
    public Employee createEmployee(EmployeeDto employeeDTO) {
        Employee employee = modelMapper.map(employeeDTO, Employee.class);

        Office office = officeRepository.findById(String.valueOf(employeeDTO.getOfficeCode())).orElseThrow(() -> new EntityNotFoundException("Office not found with ID: " + employeeDTO.getOfficeCode()));
        employee.setOfficeCode(office);

        return employeeRepository.save(employee);
    }

    @Transactional
    public Employee updateEmployee(Integer id, EmployeeDto updateEmployee) {
        if (id != null && updateEmployee.getId() != null) {
            return employeeRepository.findById(id)
                    .map(employee -> {
                        employee.setFirstName(updateEmployee.getFirstName());
                        employee.setLastName(updateEmployee.getLastName());
                        employee.setJobTitle(updateEmployee.getJobTitle());
                        employee.setOfficeCode(officeRepository.findById(String.valueOf(updateEmployee.getOfficeCode())).orElseThrow(() -> new EntityNotFoundException("Office not found with ID: " + updateEmployee.getOfficeCode())));
                        employee.setExtension(updateEmployee.getExtension());
                        employee.setEmail(updateEmployee.getEmail());
                        return employeeRepository.save(employee);
                    })
                    .orElseGet(() -> employeeRepository.save(modelMapper.map(updateEmployee, Employee.class)));
        }
        return employeeRepository.save(modelMapper.map(updateEmployee, Employee.class));
    }
}