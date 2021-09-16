package epam.rostislav.dto.validators;

import epam.rostislav.dto.Employee;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DismissalDateValidatorImpl implements ConstraintValidator<DismissalDateValidator, Employee> {
    @Override
    public boolean isValid(Employee employee, ConstraintValidatorContext constraintValidatorContext) {
        return employee.getDismissalDate() == null || employee.getDismissalDate().after(employee.getEmploymentDate());
    }
}
