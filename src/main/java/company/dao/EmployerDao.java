package company.dao;

import company.model.Department;
import company.model.Employee;
import java.sql.SQLException;
import java.util.List;

public interface EmployerDao {
    void addEmployee(Employee employee) throws SQLException;
    List<Employee> getAllEmployees() throws SQLException;
    Employee getEmployeeById(int id) throws SQLException;
    void giveManagerStatus(int employeeId, int departmentId) throws SQLException;
}
