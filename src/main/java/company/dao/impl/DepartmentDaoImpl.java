package company.dao.impl;

import company.dao.DepartmentDao;
import company.model.Department;
import company.utils.AppConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.sql.*;

@Repository
public class DepartmentDaoImpl implements DepartmentDao {

    private final Connection connection;

    @Autowired
    public DepartmentDaoImpl(AppConnection connection) {
        this.connection = connection.getConnection();
    }


    @Override
    public void addDepartment(Department department) throws SQLException {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("INSERT INTO departments (parent_id, department_name, creation_date) VALUES(?,?,?)")) {

            preparedStatement.setInt(1, department.getId());
            preparedStatement.setString(2, department.getDepartmentName());
            preparedStatement.setDate(3, (Date) department.getDateOfCreation());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public Department getDepartmentById(int id) throws SQLException {
        Department department = new Department();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM departments WHERE id = " + id);

            while (resultSet.next()) {
                department.setDepartmentName(resultSet.getString("department_name"));
                department.setDateOfCreation(resultSet.getDate("creation_date"));
                department.setId(resultSet.getInt("id"));
                department.setParentId(resultSet.getInt("parent_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (connection != null) {
                connection.close();
            }
        }
        return department;
    }

}
