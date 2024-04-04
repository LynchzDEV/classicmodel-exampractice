package cock.and.ball.exampractice.repository;

import cock.and.ball.exampractice.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}