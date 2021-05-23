package com.atmoterm.atmoterm_application.domain.team;

import com.atmoterm.atmoterm_application.domain.employee.Employee;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "Teams")
public class Team {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;

    @NotBlank(message = "Team name can't be empty")
    @Size(min = 1, max = 50, message = "Team name must be between 1 and 50 characters")
    @Column(name = "TEAM_NAME")
    private String name;

    @ManyToMany(mappedBy = "teams")
    @JoinTable(
            name = "JOIN_TEAM_EMPLOYEE",
            joinColumns = @JoinColumn(name = "TEAM_ID"),
            inverseJoinColumns = @JoinColumn(name = "EMPLOYEE_ID")
    )
    private List<Employee> employees = new ArrayList<>();

    public void addEmployee(Employee employee) {
        employees.add(employee);
        employee.getTeams().add(this);
    }

    public Team(String name) {
        this.name = name;
    }

    public Team(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
