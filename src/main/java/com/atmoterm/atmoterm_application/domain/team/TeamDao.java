package com.atmoterm.atmoterm_application.domain.team;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TeamDao extends JpaRepository<Team, Long> {

    List<Team> findAll();

    Optional<Team> findById(Long id);

    boolean existsById(Long id);

    Team save(Team team);

    void deleteById(Long id);

}
