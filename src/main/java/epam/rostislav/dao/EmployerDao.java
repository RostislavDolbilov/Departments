package epam.rostislav.dao;

import epam.rostislav.dto.Employee;
import epam.rostislav.dto.EmployeeInfo;
import org.springframework.stereotype.Component;
import java.sql.SQLException;
import java.util.ArrayList;

@Component
public interface EmployerDao {
    void addEmployee(Employee employee) throws SQLException;
    EmployeeInfo getEmployeeById(int id) throws SQLException;
    void giveManagerStatus(int employeeId);
    ArrayList<Employee> getAllEmployees() throws SQLException;

}
