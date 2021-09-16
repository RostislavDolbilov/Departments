package epam.rostislav.dao;

import epam.rostislav.dto.DepartmentInfo;
import org.springframework.stereotype.Component;

@Component
public interface DepartmentDao {
    void aadDepartment(DepartmentDao department);
    DepartmentInfo fullDepartmentInfo(int departmentId);
}
