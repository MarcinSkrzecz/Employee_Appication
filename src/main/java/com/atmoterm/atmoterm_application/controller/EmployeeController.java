package com.atmoterm.atmoterm_application.controller;
import com.atmoterm.atmoterm_application.domain.employee.EmployeeDto;
import com.atmoterm.atmoterm_application.mapper.EmployeeMapper;
import com.atmoterm.atmoterm_application.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private EmployeeMapper mapper;
    @Autowired
    private EmployeeService services;

    @RequestMapping(method = RequestMethod.GET, value = "/employee")
    public List<EmployeeDto> getAllEmployees() {
        return mapper.mapToEmployeeDto(services.getAllEmployees());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/employee_active")
    public List<EmployeeDto> getAllActiveEmployees() {
        return mapper.mapToEmployeeDto(services.getAllActiveEmployees());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/employee/{employeeId}")
    public EmployeeDto getEmployeeById(@RequestParam Long employeeId) {
        return mapper.mapToEmployeeDto(services.getEmployeeById(employeeId));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/employee", consumes = APPLICATION_JSON_VALUE)
    public EmployeeDto createEmployee(@RequestBody @Valid EmployeeDto employeeDto) {
        return mapper.mapToEmployeeDto(services.createEmployee(mapper.mapToEmployee(employeeDto)));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/employee", consumes = APPLICATION_JSON_VALUE)
    public EmployeeDto updateEmployee(@RequestBody @Valid EmployeeDto employeeDto) {
        return mapper.mapToEmployeeDto(services.updateEmployee(mapper.mapToEmployee(employeeDto)));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/employee/{employeeId}")
    public void deleteEmployee(@RequestParam Long employeeId) {
        services.deleteEmployee(employeeId);
    }

}
