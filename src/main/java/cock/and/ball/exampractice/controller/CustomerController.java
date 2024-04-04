package cock.and.ball.exampractice.controller;

import cock.and.ball.exampractice.dto.CustomerDto;
import cock.and.ball.exampractice.entity.Customer;
import cock.and.ball.exampractice.model.ErrorResponse;
import cock.and.ball.exampractice.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("")
    public ResponseEntity<Page<Customer>> getAllCustomer(Pageable pageable) {
        return ResponseEntity.ok(customerService.getAllCustomers(pageable));
    }

    @GetMapping("/{customerNumber}")
    public ResponseEntity<Object> getCustomerWithOrders(@PathVariable Long customerNumber) {
        Optional<CustomerDto> customerDtoOptional = customerService.getCustomerDTOById(Math.toIntExact(customerNumber));
        return customerDtoOptional.<ResponseEntity<Object>>map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("Customer not found")));
    }


}
