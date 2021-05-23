package com.atmoterm.atmoterm_application.service;

import com.atmoterm.atmoterm_application.domain.employee.Employee;
import com.atmoterm.atmoterm_application.domain.employee.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeDao employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public List<Employee> getAllActiveEmployees() {
        return employeeRepository.findEmployeesByDateOfEmploymentIsNotNullAndSalaryIsNotNull();
    }

    public Employee getEmployeeById(final Long id) {

        if (employeeRepository.findById(id).isPresent()) {
            return employeeRepository.findById(id).get();
        } else throw new IllegalArgumentException("Employee not found");
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Employee employee) {
        if (employeeRepository.existsById(employee.getId())) {

            return employeeRepository.save(employee);

        } else throw new IllegalArgumentException("Employee not found");
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    //
    public List<Employee> getEmployeesListByIds(List<Long> employeesIds) {
        List<Employee> employeeList = new ArrayList<>();
        for (int i = 0; i <= employeesIds.size(); i++) {
            if (employeeRepository.existsById(employeesIds.get(i))) {
                employeeList.add(getEmployeeById(employeesIds.get(i)));
            }
        }
        return employeeList;
    }

}
