package com.atmoterm.atmoterm_application.mapper;

import com.atmoterm.atmoterm_application.domain.employee.Employee;
import com.atmoterm.atmoterm_application.domain.employee.EmployeeDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeMapper {

    // create-2 cases + get
    public Employee mapToEmployee(final EmployeeDto employeeDto) {
        if (employeeDto.getId() == null) {
            if (employeeDto.getSalary() == null && employeeDto.getDateOfEmployment() == null) {
                return new Employee(
                        employeeDto.getName()
                );
            } else if (employeeDto.getSalary() != null && employeeDto.getDateOfEmployment() != null) {
                return new Employee(
                        employeeDto.getName(),
                        employeeDto.getSalary(),
                        employeeDto.getDateOfEmployment()
                );
            } else throw new IllegalArgumentException("Wrong Employee input format");
        } else {
            return new Employee(
                    employeeDto.getId(),
                    employeeDto.getName(),
                    employeeDto.getSalary(),
                    employeeDto.getDateOfEmployment()
            );
        }
    }

    public EmployeeDto mapToEmployeeDto(final Employee employee) {
        return new EmployeeDto(
                employee.getId(),
                employee.getName(),
                employee.getSalary(),
                employee.getDateOfEmployment()
        );
    }

    public List<EmployeeDto> mapToEmployeeDto(final List<Employee> employeeList) {
        return employeeList.stream()
                .map(this::mapToEmployeeDto)
                .collect(Collectors.toList());
    }
}
