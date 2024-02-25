package com.example.demoStadium.services;

import com.example.demoStadium.entities.Stadium;
import com.example.demoStadium.entities.Team;
import com.example.demoStadium.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    public List<Team> findAll() {
        return teamRepository.findAll();
    }

    public Team findById(Integer id){
        Optional<Team> optional = teamRepository.findById(id);
        return optional.isPresent() ? optional.get() : null;
    }

    public Team save(Team team, Stadium stadium) {
        team.setStadium(stadium);
        return teamRepository.save(team);
    }

    public Team update(Integer id, Team updatedTeam){
        Team team = findById(id);
        if(team != null) {
            team.setName(updatedTeam.getName());
            team.setDateFoundation(updatedTeam.getDateFoundation());
            team.setConfederation(updatedTeam.getConfederation());
            team.setStadium(updatedTeam.getStadium());
            return teamRepository.save(team);
        } else {
            throw new RuntimeException("Team not found with id: " + id);
        }
    }

    public Team delete(Integer id){
        Team team = findById(id);
        Team deleted = new Team();
        if (team != null) {
            deleted = team;
            teamRepository.delete(team);
            return deleted;
        } else {
            throw new RuntimeException("Team not found with id: " + id);
        }
    }
}
