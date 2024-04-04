package cock.and.ball.exampractice.controller;

import cock.and.ball.exampractice.entity.Product;
import cock.and.ball.exampractice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/pageable")
    public ResponseEntity<Page<Product>> listProducts(Pageable pageable) {
        return ResponseEntity.ok(productService.listAllProductPageable(pageable));
    }

    @GetMapping("")
    public Page<Product> getProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "productName,asc") String[] sort) {
        return productService.getProducts(page, size, sort);
    }
}
