package company.test;
import company.dao.impl.DepartmentDaoImpl;
import company.dao.impl.EmployerDaoImpl;
import company.model.Department;
import company.model.Employee;
import company.utils.AppConnection;
import java.sql.SQLException;

public class ConnectionTest {
    public static void main(String[] args) throws SQLException {

        DepartmentDaoImpl departmentDao = new DepartmentDaoImpl(new AppConnection());
        Department department = departmentDao.getDepartmentById(1);
        System.out.println(department);

        System.out.println("_____________________________________\n");

        EmployerDaoImpl employerDao = new EmployerDaoImpl(new AppConnection());
        Employee employee = employerDao.getEmployeeById(1);
        System.out.println(employee) ;
        employerDao.giveManagerStatus(1,4);
        Employee updatedEmployee = employerDao.getEmployeeById(1);
        System.out.println(updatedEmployee) ;
    }
}
