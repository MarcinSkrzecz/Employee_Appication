package com.atmoterm.atmoterm_application.mapper;

import com.atmoterm.atmoterm_application.domain.employee.Employee;
import com.atmoterm.atmoterm_application.domain.team.Team;
import com.atmoterm.atmoterm_application.domain.team.TeamDto;
import com.atmoterm.atmoterm_application.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TeamMapper {

    @Autowired
    private EmployeeService employeeService;

    public Team mapToTeam(final TeamDto teamDto) {
        if (teamDto.getId() == null) {
            return new Team(
                    teamDto.getName()
            );
        } else {
            return new Team(
                    teamDto.getId(),
                    teamDto.getName(),
                    employeeService.getEmployeesListByIds(teamDto.getEmployeesIds())
            );
        }
    }

    public TeamDto mapToTeamDto(final Team team) {
        return new TeamDto(
                team.getId(),
                team.getName(),
                team.getEmployees().stream().map(Employee::getId).collect(Collectors.toList())
        );
    }

    public List<TeamDto> mapToTeamDto(final List<Team> teamList) {
        return teamList.stream()
                .map(this::mapToTeamDto)
                .collect(Collectors.toList());
    }
}
