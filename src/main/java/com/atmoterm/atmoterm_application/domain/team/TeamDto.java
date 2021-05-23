package com.atmoterm.atmoterm_application.domain.team;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class TeamDto {

    private Long id;
    private String name;
    private List<Long> employeesIds;

    public TeamDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public TeamDto(Long id, String name, List<Long> employeesIds) {
        this.id = id;
        this.name = name;
        this.employeesIds = employeesIds;
    }
}
