package com.example.demoStadium.repositories;

import com.example.demoStadium.entities.Stadium;
import com.example.demoStadium.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {

    List<Team> findByStadium(Stadium stadium);
}
