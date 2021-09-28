package company.constraint.validators;

import company.model.Employee;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DismissalDateValidatorImpl implements ConstraintValidator<DismissalDateValidator, Employee> {
    @Override
    public boolean isValid(Employee employee, ConstraintValidatorContext constraintValidatorContext) {
        return employee.getDismissalDate() == null || employee.getDismissalDate().after(employee.getEmploymentDate());
    }
}
