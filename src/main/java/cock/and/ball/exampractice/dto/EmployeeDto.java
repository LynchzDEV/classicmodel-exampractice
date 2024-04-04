package cock.and.ball.exampractice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class EmployeeDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String extension;
    private String email;
    private String jobTitle;
    private Integer officeCode;
}
