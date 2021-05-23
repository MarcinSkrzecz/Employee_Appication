package com.atmoterm.atmoterm_application.controller;

import com.google.gson.Gson;
import com.atmoterm.atmoterm_application.domain.employee.Employee;
import com.atmoterm.atmoterm_application.domain.employee.EmployeeDto;
import com.atmoterm.atmoterm_application.mapper.EmployeeMapper;
import com.atmoterm.atmoterm_application.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTests {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EmployeeMapper mapper;
    @MockBean
    private EmployeeService service;

    @Test
    public void testGetEmptyEmployees() throws Exception {
        //Given
        List<Employee> employeeList = new ArrayList<>();
        List<EmployeeDto> employeeDtoList = new ArrayList<>();

        when(service.getAllEmployees()).thenReturn(employeeList);
        when(mapper.mapToEmployeeDto(employeeList)).thenReturn(employeeDtoList);

        //When & Then
        mockMvc.perform(get("/api/employee")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void testGetAllEmployees() throws Exception {
        //Given
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee(1L, "testName"));
        List<EmployeeDto> employeeDtoList = new ArrayList<>();
        employeeDtoList.add(new EmployeeDto(1L, "testName"));

        when(service.getAllEmployees()).thenReturn(employeeList);
        when(mapper.mapToEmployeeDto(employeeList)).thenReturn(employeeDtoList);

        //When & Then
        mockMvc.perform(get("/api/employee")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("testName")));
    }

    @Test
    public void testGetAllActiveEmployees() throws Exception {
        //Given
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee(1L, "testName", 123.45, LocalDate.of(2021, 05,23)));
        List<EmployeeDto> employeeDtoList = new ArrayList<>();
        employeeDtoList.add(new EmployeeDto(1L, "testName", 123.45, LocalDate.of(2021, 05,23)));

        when(service.getAllActiveEmployees()).thenReturn(employeeList);
        when(mapper.mapToEmployeeDto(employeeList)).thenReturn(employeeDtoList);

        //When & Then
        mockMvc.perform(get("/api/employee_active")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("testName")))
                .andExpect(jsonPath("$[0].salary", is(123.45)))
                .andExpect(jsonPath("$[0].dateOfEmployment", is("2021-05-23")));
    }

    @Test
    public void testGetEmployeeById() throws Exception {
        //Given
        Employee employee = new Employee(1L, "testName");
        EmployeeDto employeeDto = new EmployeeDto(1L, "testName");

        when(service.getEmployeeById(any())).thenReturn(employee);
        when(mapper.mapToEmployeeDto(employee)).thenReturn(employeeDto);

        //When & Then
        mockMvc.perform(get("/api/employee/{employeeId}", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .param("employeeId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", is(1)))
                .andExpect(jsonPath("name", is("testName")));

    }

    @Test
    public void testCreateEmployee() throws Exception {
        //Given
        Employee employee = new Employee(1L, "testName");
        EmployeeDto employeeDto = new EmployeeDto(1L, "testName");

        when(service.getEmployeeById(any())).thenReturn(employee);
        when(mapper.mapToEmployeeDto(employee)).thenReturn(employeeDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(employeeDto);

        //When & Then
        mockMvc.perform(post("/api/employee")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateEmployee() throws Exception {
        //Given
        Employee employee = new Employee(1L, "testName");
        EmployeeDto employeeDto = new EmployeeDto(1L, "testName");

        when(service.getEmployeeById(any())).thenReturn(employee);
        when(mapper.mapToEmployeeDto(employee)).thenReturn(employeeDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(employeeDto);

        //When & Then
        mockMvc.perform(put("/api/employee")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteEmployee() throws Exception {
        mockMvc.perform(delete("/api/employee/{employeeId}", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .param("employeeId", "1"))
                .andExpect(status().isOk());
    }

}
