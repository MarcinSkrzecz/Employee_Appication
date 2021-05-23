package com.atmoterm.atmoterm_application.domain;
import com.atmoterm.atmoterm_application.domain.employee.Employee;
import com.atmoterm.atmoterm_application.domain.employee.EmployeeDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class EmployeeTests {

    @Autowired
    private EmployeeDao repository;

    @Test
    public void testCreateUpdateDeleteNormalEmployee() {
        //Given
        Employee employee = new Employee(1L, "testName");
        //When
        repository.save(employee);
        //Then
        assertTrue(repository.findById(employee.getId()).isPresent());
        //Update
        employee.setName("updateName");
        repository.save(employee);
        //Then
        assertTrue(repository.findById(employee.getId()).isPresent());
        assertEquals(employee.getName(), "updateName");
        //CleanUp
        repository.deleteById(employee.getId());
        assertFalse(repository.findById(employee.getId()).isPresent());
    }

    @Test
    public void testCreateUpdateDeleteActiveEmployee() {
        //Given
        Employee employee = new Employee(1L, "testName", 123.45, LocalDate.of(2121,05,23));
        //When
        repository.save(employee);
        //Then
        assertTrue(repository.findById(employee.getId()).isPresent());
        //Update
        employee.setName("updateName");
        employee.setSalary(678.90);
        employee.setDateOfEmployment(LocalDate.of(2021,05,24));
        repository.save(employee);
        //Then
        assertTrue(repository.findById(employee.getId()).isPresent());
        assertEquals(employee.getName(), "updateName");
        assertEquals(employee.getSalary(), 678.90);
        assertEquals(employee.getDateOfEmployment(), LocalDate.of(2021,05,24));
        //CleanUp
        repository.deleteById(employee.getId());
        assertFalse(repository.findById(employee.getId()).isPresent());
    }

}
