package cock.and.ball.exampractice.repository;

import cock.and.ball.exampractice.entity.Office;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfficeRepository extends JpaRepository<Office, String> {
}