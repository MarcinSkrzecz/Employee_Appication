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
    private EmployeeDao repository;

    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    public List<Employee> getAllActiveEmployees() {
        return repository.findEmployeesByDateOfEmploymentIsNotNullAndSalaryIsNotNull();
    }

    public Employee getEmployeeById(final Long id) {

        if (repository.findById(id).isPresent()) {
            return repository.findById(id).get();
        } else throw new IllegalArgumentException("Employee not found");
    }

    public Employee createEmployee(Employee employee) {
        return repository.save(employee);
    }

    public Employee updateEmployee(Employee employee) {
        if (repository.existsById(employee.getId())) {

            return repository.save(employee);

        } else throw new IllegalArgumentException("Employee not found");
    }

    public void deleteEmployee(Long id) {
        repository.deleteById(id);
    }

    //
    public List<Employee> getEmployeesListByIds(List<Long> employeesIds) {
        List<Employee> employeeList = new ArrayList<>();
        for (int i = 0; i <= employeesIds.size(); i++) {
            if (repository.existsById(employeesIds.get(i))) {
                employeeList.add(getEmployeeById(employeesIds.get(i)));
            }
        }
        return employeeList;
    }

}
