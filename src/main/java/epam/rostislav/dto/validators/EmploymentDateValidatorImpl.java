package epam.rostislav.dto.validators;

import epam.rostislav.dto.Employee;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmploymentDateValidatorImpl implements ConstraintValidator<EmploymentDateValidator, Employee> {
    @Override
    public boolean isValid(Employee employee, ConstraintValidatorContext constraintValidatorContext) {
        return !employee.getEmploymentDate().before(employee.getBerthDay());
    }
}
