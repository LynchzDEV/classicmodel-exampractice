package cock.and.ball.exampractice.service;

import cock.and.ball.exampractice.dto.CustomerDto;
import cock.and.ball.exampractice.dto.OrderDto;
import cock.and.ball.exampractice.entity.Customer;
import cock.and.ball.exampractice.repository.CustomerRepository;
import cock.and.ball.exampractice.util.ListMapper;
import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ListMapper listMapper;

    public Optional<CustomerDto> getCustomerDTOById(Integer id) {
        return customerRepository.findById(id)
                .map(customer -> {
                    List<OrderDto> orderDTOList = listMapper.mapList(customer.getOrders(), OrderDto.class, modelMapper);
                    CustomerDto customerDTO = modelMapper.map(customer, CustomerDto.class);
                    customerDTO.setOrders(orderDTOList);
                    return Optional.of(customerDTO);
                })
                .orElse(Optional.empty());
    }


    public Page<Customer> getAllCustomers(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    public Customer findCustomerById(Long customerNumber) {
        return customerRepository.findById(Math.toIntExact(customerNumber)).orElse(null);
    }
}
