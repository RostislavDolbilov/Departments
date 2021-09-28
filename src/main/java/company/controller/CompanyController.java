package company.controller;

import company.model.*;
import company.service.DepartmentService;
import company.service.EmployeeService;
import company.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {
    private final DepartmentService departmentService;
    private final EmployeeService employeeService;

    @Autowired
    public CompanyController(DepartmentService departmentService, EmployeeService employeeService) {
        this.departmentService = departmentService;
        this.employeeService = employeeService;
    }

    @PostMapping("/add_department")
    public void addPosition(@RequestBody Department department) throws SQLException {
        departmentService.addDepartment(department);
    }

    @GetMapping("/get_department")
    public DepartmentInfo getDepartment(@RequestParam int id) throws SQLException {
        return departmentService.getDepartmentInfo(id);
    }

    @PostMapping("/add_employee")
    public void addPosition(@Valid @RequestBody Employee employee) throws SQLException {
        employeeService.addEmployee(employee);
    }

    @GetMapping("/get_employee")
    public EmployeeInfo getEmployee(@RequestParam int id) throws SQLException {
        return employeeService.getEmployeeInfoById(id);
    }
}
