package cock.and.ball.exampractice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class OrderDto {
    Integer id;
    LocalDate orderDate;
    LocalDate requiredDate;
    LocalDate shippedDate;
    String status;
    String comments;
}