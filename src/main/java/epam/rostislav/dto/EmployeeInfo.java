package epam.rostislav.dto;

import lombok.*;
import java.util.Date;

@Getter @Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeInfo {
    private int id;
    private String name;
    private String surname;
    private String patronymic;
    private String gender;
    private Date berthDay;
    private String phoneNumber;
    private String email;
    private Date employmentDate;
    private Date dismissalDate;
    private double salary;
    private String department;
    private boolean manager;
    private String position;
}
