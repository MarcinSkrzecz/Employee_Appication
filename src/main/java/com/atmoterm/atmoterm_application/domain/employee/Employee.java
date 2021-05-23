package com.atmoterm.atmoterm_application.domain.employee;


import com.atmoterm.atmoterm_application.domain.team.Team;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "Employees")
public class Employee {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue
    @Column(name = "EMPLOYEE_ID")
    private Long id;

    @NotBlank(message = "Name can't be empty")
    @Size(min = 1, max = 50, message = "Name must be between 1 and 50 characters")
    @Column(name = "EMPLOYEE_NAME")
    private String name;

    @PositiveOrZero(message = "Salary must have value equal or greater than 0")
    @Column(name = "SALARY")
    private Double salary;

    @Column(name = "DATE_OF_EMPLOYMENT")
    private LocalDate dateOfEmployment;

    @ManyToMany
    @JoinTable(
            name = "JOIN_EMPLOYEE_TEAM",
            joinColumns = @JoinColumn(name = "EMPLOYEE_ID"),
            inverseJoinColumns = @JoinColumn(name = "TEAM_ID")
    )
    private List<Team> teams = new ArrayList<>();

    public void addToTeam(Team team) {
        teams.add(team);
        team.getEmployees().add(this);
    }

    //Create normal Employee
    public Employee(String name) {
        this.name = name;
    }

    //tests
    public Employee(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    //Create Active Employee
    public Employee(String name, Double salary, LocalDate dateOfEmployment) {
        this.name = name;
        this.salary = salary;
        this.dateOfEmployment = dateOfEmployment;
    }

    //Get Employees
    public Employee(Long id, String name, Double salary, LocalDate dateOfEmployment) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.dateOfEmployment = dateOfEmployment;
    }

}
