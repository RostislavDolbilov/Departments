package company.dao.impl;

import company.dao.EmployerDao;
import company.model.Employee;
import company.utils.AppConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EmployerDaoImpl implements EmployerDao {
    private Connection connection;

    @Autowired
    public EmployerDaoImpl(AppConnection connection) {
        this.connection = connection.getConnection();
    }


    @Override
    public void addEmployee(Employee employee) throws SQLException {
        connection = new AppConnection().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO employees (name, " +
                "surname, patronymic, gender, berth_day, phone_number,\n" +
                "email, employment_date, dismissal_date, position_id, salary, " +
                "manager) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)")) {

            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getSurname());
            preparedStatement.setString(3, employee.getPatronymic());
            preparedStatement.setBoolean(4, employee.isGender());
            preparedStatement.setDate(5, (Date) employee.getBerthDay());
            preparedStatement.setString(6, employee.getPhoneNumber());
            preparedStatement.setString(7, employee.getEmail());
            preparedStatement.setDate(8, (Date) employee.getEmploymentDate());
            preparedStatement.setDate(9, (Date) employee.getDismissalDate());
            preparedStatement.setInt(10, employee.getPositionId());
            preparedStatement.setDouble(11, employee.getSalary());
            preparedStatement.setBoolean(12, employee.isManager());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }

    @Override
    public List<Employee> getAllEmployees() throws SQLException {
        connection = new AppConnection().getConnection();
        ArrayList<Employee> employeesList = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM employees");
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setId(resultSet.getInt("id"));
                employee.setName(resultSet.getString("name"));
                employee.setSurname(resultSet.getString("surname"));
                employee.setPatronymic(resultSet.getString("patronymic"));
                employee.setGender(resultSet.getBoolean("gender"));
                employee.setBerthDay(resultSet.getDate("berth_day"));
                employee.setPhoneNumber(resultSet.getString("phone_number"));
                employee.setEmail(resultSet.getString("email"));
                employee.setEmploymentDate(resultSet.getDate("employment_date"));
                employee.setDismissalDate(resultSet.getDate("dismissal_date"));
                employee.setPositionId(resultSet.getInt("position_id"));
                employee.setSalary(resultSet.getDouble("salary"));
                employee.setManager(resultSet.getBoolean("manager"));

                employeesList.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            connection.close();
        }
        return employeesList;
    }


    @Override
    public Employee getEmployeeById(int id) throws SQLException {
        connection = new AppConnection().getConnection();
        Employee employee = new Employee();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM employees WHERE id = " + id);
            while (resultSet.next()) {
                employee.setId(resultSet.getInt("id"));
                employee.setName(resultSet.getString("name"));
                employee.setSurname(resultSet.getString("surname"));
                employee.setPatronymic(resultSet.getString("patronymic"));
                employee.setGender(resultSet.getBoolean("gender"));
                employee.setBerthDay(resultSet.getDate("berth_day"));
                employee.setPhoneNumber(resultSet.getString("phone_number"));
                employee.setEmail(resultSet.getString("email"));
                employee.setEmploymentDate(resultSet.getDate("employment_date"));
                employee.setDismissalDate(resultSet.getDate("dismissal_date"));
                employee.setPositionId(resultSet.getInt("position_id"));
                employee.setDepartmentId(resultSet.getInt("department_id"));
                employee.setSalary(resultSet.getDouble("salary"));
                employee.setManager(resultSet.getBoolean("manager"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            connection.close();
        }
        return employee;
    }

    @Override
    public void giveManagerStatus(int employeeId, int departmentId) throws SQLException {
        connection = new AppConnection().getConnection();
        List<Employee> employees = getAllEmployees()
                .stream()
                .filter(department -> department.getId() == departmentId)
                .collect(Collectors.toList());
        connection = new AppConnection().getConnection();
//        if (employees.stream().noneMatch(Employee::isManager)){
            try {PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE employees SET manager = ? WHERE id = " + employeeId);

                preparedStatement.setBoolean(1, true);

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                connection.close();
            }
//        }
    }
}
