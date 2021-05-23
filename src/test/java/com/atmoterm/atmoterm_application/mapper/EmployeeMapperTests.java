package com.atmoterm.atmoterm_application.mapper;
import com.atmoterm.atmoterm_application.domain.employee.Employee;
import com.atmoterm.atmoterm_application.domain.employee.EmployeeDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class EmployeeMapperTests {

    @InjectMocks
    private EmployeeMapper mapper;

    @Test
    public void mapToToEmployeeTest_createEmployee() {
        //Given
        EmployeeDto employeeDto = new EmployeeDto("testName");
        //When
        Employee employee = mapper.mapToEmployee(employeeDto);
        //Then
        assertEquals(employeeDto.getName(), employee.getName());
    }

    @Test
    public void mapToToEmployeeTest_createActiveEmployee() {
        //Given
        EmployeeDto employeeDto = new EmployeeDto("testName", 123.45,
                LocalDate.of(2021,05,23));
        //When
        Employee employee = mapper.mapToEmployee(employeeDto);
        //Then
        assertEquals(employeeDto.getName(), employee.getName());
        assertEquals(employeeDto.getSalary(), employee.getSalary());
        assertEquals(employeeDto.getDateOfEmployment(), employee.getDateOfEmployment());
    }

    @Test
    public void mapToToEmployeeTest_normal() {
        //Given
        EmployeeDto employeeDto = new EmployeeDto(1L,"testName");
        //When
        Employee employee = mapper.mapToEmployee(employeeDto);
        //Then
        assertEquals(employeeDto.getId(), employee.getId());
        assertEquals(employeeDto.getName(), employee.getName());
    }

    @Test
    public void mapToToEmployeeTest_active() {
        //Given
        EmployeeDto employeeDto = new EmployeeDto(1L,"testName",123.45,
                LocalDate.of(2021,05,23));
        //When
        Employee employee = mapper.mapToEmployee(employeeDto);
        //Then
        assertEquals(employeeDto.getId(), employee.getId());
        assertEquals(employeeDto.getName(), employee.getName());
        assertEquals(employeeDto.getSalary(), employee.getSalary());
        assertEquals(employeeDto.getDateOfEmployment(), employee.getDateOfEmployment());
    }


    @Test
    public void mapToEmployeeDtoTest_normal() {
        //Given
        Employee employee = new Employee(1L,"testName");
        //When
        EmployeeDto employeeDto = mapper.mapToEmployeeDto(employee);
        //Then
        assertEquals(employeeDto.getId(), employee.getId());
        assertEquals(employeeDto.getName(), employee.getName());
    }

    @Test
    public void mapToEmployeeDtoTest_active() {
        //Given
        Employee employee = new Employee(1L,"testName",123.45, LocalDate.of(2021,05,23));
        //When
        EmployeeDto employeeDto = mapper.mapToEmployeeDto(employee);
        //Then
        assertEquals(employeeDto.getId(), employee.getId());
        assertEquals(employeeDto.getName(), employee.getName());
        assertEquals(employeeDto.getSalary(), employee.getSalary());
        assertEquals(employeeDto.getDateOfEmployment(), employee.getDateOfEmployment());
    }

    @Test
    public void mapToEmployeeDtoListTest() {
        //Given
        Employee employee_1 = new Employee(1L,"testName_1");
        Employee employee_2 = new Employee(2L,"testName_2",123.45, LocalDate.of(2021,05,23));
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee_1);
        employeeList.add(employee_2);
        //When
        List<EmployeeDto> employeeDtoList = mapper.mapToEmployeeDto(employeeList);
        //Then
        assertEquals(employeeList.get(0).getId(), employeeDtoList.get(0).getId());
        assertEquals(employeeList.get(0).getName(), employeeDtoList.get(0).getName());

        assertEquals(employeeList.get(1).getId(), employeeDtoList.get(1).getId());
        assertEquals(employeeList.get(1).getName(), employeeDtoList.get(1).getName());
        assertEquals(employeeList.get(1).getSalary(), employeeDtoList.get(1).getSalary());
        assertEquals(employeeList.get(1).getDateOfEmployment(), employeeDtoList.get(1).getDateOfEmployment());

        assertEquals(employeeList.size(), employeeDtoList.size());
    }
}
