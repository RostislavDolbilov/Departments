package company.service;

import company.dao.impl.DepartmentDaoImpl;
import company.model.Department;
import company.model.DepartmentInfo;
import company.model.Employee;
import company.model.EmployeeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.SQLException;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    private final DepartmentDaoImpl departmentDao;
    private final EmployeeService employeeService;

    @Autowired
    public DepartmentService(DepartmentDaoImpl departmentDao, EmployeeService employeeService) {
        this.departmentDao = departmentDao;
        this.employeeService = employeeService;
    }

    public void addDepartment(Department department) throws SQLException{
        departmentDao.addDepartment(department);
    }

    public Department getDepartmentById(int id) throws SQLException{
        return departmentDao.getDepartmentById(id);
    }


    public DepartmentInfo getDepartmentInfo(final int departmentId) throws SQLException {
        DepartmentInfo departmentInfo = new DepartmentInfo();
        Department department = getDepartmentById(departmentId);

        departmentInfo.setDepartmentName(department.getDepartmentName());
        departmentInfo.setDateOfCreation(department.getDateOfCreation());
        departmentInfo.setWorkersAmount(employeeService.getAllEmployees()
                .stream()
                .filter(employee -> employee.getDepartmentId() == departmentId)
                .toArray().length);
        departmentInfo.setManager(employeeService.getAllEmployees()
                .stream()
                .filter(employee -> employee.getDepartmentId() == departmentId)
                .filter(Employee::isManager)
                .map(Employee::getName)
                .toString());
        return departmentInfo;
    }
}
