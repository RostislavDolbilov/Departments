package epam.rostislav.service;

import epam.rostislav.dao.EmployerDao;
import epam.rostislav.dto.Employee;
import epam.rostislav.dto.EmployeeInfo;
import epam.rostislav.dto.enums.Gender;
import epam.rostislav.utils.AppConnection;
import org.springframework.stereotype.Service;
import java.sql.*;
import java.util.ArrayList;

@Service
public class EmployeeService extends AppConnection implements EmployerDao {
    private final Connection connection;
    private final PositionService positionService;

    public EmployeeService(PositionService positionService) {
        this.positionService = positionService;
        this.connection = getConnection();
    }

    @Override
    public void addEmployee(Employee employee) throws SQLException {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("INSERT INTO employees (name, " +
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
            connection.close();
            e.printStackTrace();
        }
    }

    @Override
    public EmployeeInfo getEmployeeById(int id) throws SQLException {
        EmployeeInfo employee = new EmployeeInfo();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM employees WHERE id = " + id);
            while (resultSet.next()) {
                employee.setId(resultSet.getInt("id"));
                employee.setName(resultSet.getString("name"));
                employee.setSurname(resultSet.getString("surname"));
                employee.setPatronymic(resultSet.getString("patronymic"));
                employee.setGender(resultSet.getBoolean("gender")?Gender.MALE.getGender() : Gender.FEMALE.getGender());
                employee.setBerthDay(resultSet.getDate("berth_day"));
                employee.setPhoneNumber(resultSet.getString("phone_number"));
                employee.setEmail(resultSet.getString("email"));
                employee.setEmploymentDate(resultSet.getDate("employment_date"));
                employee.setDismissalDate(resultSet.getDate("dismissal_date"));
                employee.setPosition(positionService.getPositionById(resultSet.getInt("position_id")).getPosition());
                employee.setSalary(resultSet.getDouble("salary"));
                employee.setManager(resultSet.getBoolean("manager"));
            }
        } catch (SQLException e) {
            connection.close();
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    public void giveManagerStatus(int employeeId) {

    }

    @Override
    public ArrayList<Employee> getAllEmployees() throws SQLException {
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
            connection.close();
            e.printStackTrace();
        }
        return employeesList;
    }
}
