package cock.and.ball.exampractice.repository;

import cock.and.ball.exampractice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}