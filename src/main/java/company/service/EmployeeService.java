package company.service;

import company.dao.impl.EmployerDaoImpl;
import company.model.Department;
import company.model.Employee;
import company.model.EmployeeInfo;
import company.model.enums.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService{
    private final PositionService positionService;
    private final EmployerDaoImpl employerDao;
    private final DepartmentService departmentService;

    @Autowired
    public EmployeeService(PositionService positionService,
                           EmployerDaoImpl employerDao,
                           DepartmentService departmentService) {
        this.positionService = positionService;
        this.employerDao = employerDao;
        this.departmentService = departmentService;
    }

    public void addEmployee(Employee employee) throws SQLException {
        employerDao.addEmployee(employee);
    }

    public List<Employee> getAllEmployees() throws SQLException{
        return employerDao.getAllEmployees();
    }

    public Employee getEmployeeById(int id) throws SQLException{
        return employerDao.getEmployeeById(id);
    }

    public EmployeeInfo getEmployeeInfoById(int id) throws SQLException{
        EmployeeInfo employeeInfo = new EmployeeInfo();
        Employee employee = employerDao.getEmployeeById(id);

        employeeInfo.setId(employee.getId());
        employeeInfo.setName(employee.getName());
        employeeInfo.setSurname(employee.getSurname());
        employeeInfo.setPatronymic(employee.getPatronymic());
        employeeInfo.setGender(employee.isGender()? Gender.MALE.getGender() : Gender.FEMALE.getGender());
        employeeInfo.setBerthDay(employee.getBerthDay());
        employeeInfo.setPhoneNumber(employee.getPhoneNumber());
        employeeInfo.setEmail(employee.getEmail());
        employeeInfo.setEmploymentDate(employee.getEmploymentDate());
        employeeInfo.setDismissalDate(employee.getDismissalDate());
        employeeInfo.setSalary(employee.getSalary());
        employeeInfo.setManager(employee.isManager());
        employeeInfo.setPosition(positionService
                .getPositionById(employee.getPositionId())
                .getPosition());
        employeeInfo.setDepartment(departmentService
                .getDepartmentById(employee.getDepartmentId())
                .getDepartmentName());

            return employeeInfo;
    }

    public void giveManagerStatus(int id) throws SQLException {
        Employee employee = getEmployeeById(id);
        employerDao.giveManagerStatus(id, employee.getDepartmentId());
    }
}
