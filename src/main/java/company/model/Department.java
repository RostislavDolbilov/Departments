package company.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

@Getter @Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Department {

    private int id;
    private int parentId;
    private String departmentName;
    @DateTimeFormat(pattern="MM/dd/yyyy")
    private Date dateOfCreation;
}
