package com.example.demoStadium.services;

import com.example.demoStadium.entities.City;
import com.example.demoStadium.entities.Stadium;
import com.example.demoStadium.repositories.StadiumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StadiumService {

    @Autowired
    private StadiumRepository stadiumRepository;

    public List<Stadium> findAll() {
        return stadiumRepository.findAll();
    }

    public Stadium findById(Integer id) {
        Optional<Stadium> optional = stadiumRepository.findById(id);

        return optional.isPresent() ? optional.get() : null;
    }

    public Stadium save(Stadium stadium, City city){
        stadium.setCity(city);
        return stadiumRepository.save(stadium);
    }

    public Stadium update(Integer id, Stadium updatedStadium) {
        Stadium stadium = findById(id);
        if (stadium != null) {
            stadium.setName(updatedStadium.getName());
            stadium.setCity(updatedStadium.getCity());
            stadium.setCapacity(updatedStadium.getCapacity());

            return stadiumRepository.save(stadium);
        } else {
            throw new RuntimeException("Stadium not found with id: " + id);
        }
    }

    public void delete(Integer id) {
        Stadium stadium = findById(id);
        if (stadium != null) {
            stadiumRepository.delete(stadium);
        } else {
            throw new RuntimeException("Stadium not found with id: " + id);
        }
    }

    /*public List<Stadium> findByCountry(String country) {
        return stadiumRepository.findByCountry(country);
    }*/

    public List<Stadium> findByName(String name) {
        return stadiumRepository.findByName(name);
    }
}
