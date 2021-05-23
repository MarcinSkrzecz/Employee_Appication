package com.atmoterm.atmoterm_application.service;
import com.atmoterm.atmoterm_application.domain.employee.Employee;
import com.atmoterm.atmoterm_application.domain.employee.EmployeeDao;
import com.atmoterm.atmoterm_application.domain.employee.EmployeeDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class EmployeeServiceTests {

    @InjectMocks
    private EmployeeService service;
    @Mock
    private EmployeeDao repository;

    @Test
    public void testGetAllEmployees() {
        //Given
        when(repository.findAll()).thenReturn(new ArrayList<>());

        //When & Then
        assertEquals(0, service.getAllEmployees().size());
        verify(repository, times(1)).findAll();
    }

    @Test
    public void testGetAllActiveEmployees() {
        //Given
        when(repository.findEmployeesByDateOfEmploymentIsNotNullAndSalaryIsNotNull()).thenReturn(new ArrayList<>());

        //When & Then
        assertEquals(0, service.getAllActiveEmployees().size());
        verify(repository, times(1)).findEmployeesByDateOfEmploymentIsNotNullAndSalaryIsNotNull();
    }

    @Test
    public void testFindEmployeeById() {
        //Given
        Employee employee = new Employee(1L,"testName");

        when(repository.findById(any())).thenReturn(Optional.of(employee));

        //When
        Employee searchedEmployee = service.getEmployeeById(1L);

        //Then
        assertEquals("testName", searchedEmployee.getName());

        verify(repository, times(2)).findById(1L);
    }

    @Test
    public void createEmployee() {
        //Given
        Employee employee = new Employee(1L,"testName");
        //When
        service.createEmployee(employee);
        //Then
        verify(repository, times(1)).save(employee);
    }

    @Test
    public void testDeleteEmployee() {
        //Given
        //When
        service.deleteEmployee(1L);
        //Then
        verify(repository, times(1)).deleteById(1L);
    }

}
