package com.example.demoStadium.services;

import com.example.demoStadium.entities.City;
import com.example.demoStadium.entities.Stadium;
import com.example.demoStadium.repositories.CityRepository;
import com.example.demoStadium.repositories.StadiumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private StadiumService stadiumService;

    public List<City> findAll() {
        return cityRepository.findAll();
    }

    public City findById(Integer id) {
        Optional<City> optionalCity = cityRepository.findById(id);
        return optionalCity.isPresent() ? optionalCity.get() : null;
    }

    public City save(City city) {
        return cityRepository.save(city);
    }

    public City update(Integer id, City updatedCity) {
        City city= findById(id);
        if (city != null) {
            city.setName(updatedCity.getName());
            city.setCountry(updatedCity.getCountry());
            city.setStadiums(updatedCity.getStadiums());
            return cityRepository.save(city);
        } else {
            throw new RuntimeException("City not found with id: " + id);
        }
    }

    public City delete(Integer id){
        City city = findById(id);
        City deleted = new City();
        if (city != null) {
            List<Stadium> stsCity = city.getStadiums();
            for (Stadium st: stsCity) {
                stadiumService.delete(st.getId());
            }
            deleted = city;
            cityRepository.delete(city);
            return deleted;
        } else {
            throw new RuntimeException("City not found with id: " + id);
        }
    }
}
