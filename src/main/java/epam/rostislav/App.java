package epam.rostislav;

import epam.rostislav.dto.Employee;
import epam.rostislav.dto.Position;
import epam.rostislav.service.EmployeeService;
import epam.rostislav.service.PositionService;
import java.sql.SQLException;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws SQLException {
        PositionService positionService = new PositionService();
        EmployeeService employeeService = new EmployeeService(positionService);


//       positionService.addPosition(new Position(9, "singer"));


        ArrayList<Position> positions = positionService.getAllPositions();
        for (Position p:
             positions) {
            System.out.println(p);
        }
        System.out.println("----------------------------------------------------------");


        ArrayList<Employee> employees = employeeService.getAllEmployees();
        for (Employee e :
             employees) {
            System.out.println(e);
        }
        System.out.println("----------------------------------------------------------");

        System.out.println(employeeService.getEmployeeById(1));
        System.out.println("----------------------------------------------------------");
        System.out.println(employeeService.getEmployeeById(6));
        System.out.println("----------------------------------------------------------");




    }
}
