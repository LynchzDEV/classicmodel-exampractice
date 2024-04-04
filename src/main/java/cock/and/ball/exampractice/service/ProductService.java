package cock.and.ball.exampractice.service;

import cock.and.ball.exampractice.entity.Product;
import cock.and.ball.exampractice.repository.ProductRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;


import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> listAllProductPageable(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Page<Product> getProducts(int page, int size, String[] sort) {
        List<Order> orders = new ArrayList<>();
        for (String sortParam : sort) {
            String[] _sort = sortParam.split(",");
            Order order = new Order(getSortDirection(_sort[1]), _sort[0]);
            orders.add(order);
        }
        Pageable pageable = PageRequest.of(page, size, Sort.by(orders));
        return productRepository.findAll(pageable);
    }

    private Sort.Direction getSortDirection(String direction) {
        return "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
    }
}
