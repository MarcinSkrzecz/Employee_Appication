package com.atmoterm.atmoterm_application.domain.employee;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Getter
public class EmployeeDto {

    private Long id;
    private String name;
    private Double salary;
    private LocalDate dateOfEmployment;
    private List<Long> teamsIds;


    public EmployeeDto(String name) {
        this.name = name;
    }

    public EmployeeDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public EmployeeDto(String name, Double salary, LocalDate dateOfEmployment) {
        this.name = name;
        this.salary = salary;
        this.dateOfEmployment = dateOfEmployment;
    }

    public EmployeeDto(Long id, String name, Double salary, LocalDate dateOfEmployment) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.dateOfEmployment = dateOfEmployment;
    }

}
