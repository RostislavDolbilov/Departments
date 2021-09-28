package company.dao;

import company.model.Department;
import company.model.DepartmentInfo;

import java.sql.SQLException;

public interface DepartmentDao {
    void addDepartment(Department department) throws SQLException;
    Department getDepartmentById(int id) throws SQLException;
}
