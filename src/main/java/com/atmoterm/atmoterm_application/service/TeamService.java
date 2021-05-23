package com.atmoterm.atmoterm_application.service;

import com.atmoterm.atmoterm_application.domain.team.Team;
import com.atmoterm.atmoterm_application.domain.team.TeamDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {
    @Autowired
    private TeamDao repository;

    public List<Team> getAllTeams() {
        return repository.findAll();
    }

    public Team getTeamById(final Long id) {
        if (repository.findById(id).isPresent()) {
            return repository.findById(id).get();
        } else throw new IllegalArgumentException("Team not found");
    }

    public Team createTeam(Team team) {
        return repository.save(team);
    }

    public Team updateTeam(Team team) {
        if (repository.existsById(team.getId())) {

            return repository.save(team);

        } else throw new IllegalArgumentException("Team not found");
    }

    public void deleteTeam(Long id) {
        repository.deleteById(id);
    }

}
