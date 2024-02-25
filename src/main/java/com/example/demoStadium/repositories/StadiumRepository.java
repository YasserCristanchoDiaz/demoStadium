package com.example.demoStadium.repositories;

import com.example.demoStadium.entities.Stadium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StadiumRepository extends JpaRepository<Stadium, Integer> {

    @Query("SELECT s FROM Stadium s WHERE s.name LIKE CONCAT('%',:name, '%')")
    public List<Stadium> findByName(String name);

    //public List<Stadium> findByCountry(String country);
}
