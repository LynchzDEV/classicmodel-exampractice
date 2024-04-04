package cock.and.ball.exampractice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CustomerDto {
    Integer id;
    String customerName;
    String contactLastName;
    String contactFirstName;
    String phone;
    String addressLine1;
    String addressLine2;
    String city;
    String state;
    String postalCode;
    String country;
    List<OrderDto> orders;
}