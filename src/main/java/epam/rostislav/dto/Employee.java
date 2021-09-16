package epam.rostislav.dto;

import epam.rostislav.dto.validators.DismissalDateValidator;
import epam.rostislav.dto.validators.EmploymentDateValidator;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Getter @Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EmploymentDateValidator(message = "Invalid employment date. Date must be after berth day")
@DismissalDateValidator(message = "Invalid dismissal date. Date must be after employment date")
public class Employee {
    private int id;
    @Pattern(regexp = "^[а-яА-Я -]+$",
            message = "Name is not correctly. Use only Russian letters, space and dash")
    private String name;
    @Pattern(regexp = "^[а-яА-Я -]+$",
            message = "Surname is not correctly. Use only Russian letters, space and dash")
    private String surname;
    @Pattern(regexp = "^[а-яА-Я -]+$",
            message = "Patronymic is not correctly. Use only Russian letters, space and dash")
    private String patronymic;
    /* true - male, false - female */
    private boolean gender;
    @DateTimeFormat(pattern="MM/dd/yyyy")
    private Date berthDay;
    @Pattern(regexp = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$",
            message = "Wrong phone number. Use only numbers 0-9, space, + , round brackets")
    private String phoneNumber;
    @Email
    private String email;
    @DateTimeFormat(pattern="MM/dd/yyyy")
    private Date employmentDate;
    @DateTimeFormat(pattern="MM/dd/yyyy")
    private Date dismissalDate;
    @Digits(integer=7, fraction=1, message = "Wrong salary format")
    private double salary;
    private int departmentId;
    private boolean manager;
    private int positionId;
}

