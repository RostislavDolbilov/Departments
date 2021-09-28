package company.model;

import lombok.*;
import java.util.Date;

@Getter @Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentInfo {
    private String departmentName;
    private Date dateOfCreation;
    private int workersAmount;
    private String manager;
}
