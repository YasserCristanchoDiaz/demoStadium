package com.example.demoStadium.controllers;

import com.example.demoStadium.entities.City;
import com.example.demoStadium.entities.Stadium;
import com.example.demoStadium.response.ResponseHandler;
import com.example.demoStadium.services.CityService;
import com.example.demoStadium.services.StadiumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stadium")
public class StadiumController {

    @Autowired
    private StadiumService stadiumService;

    @Autowired
    private CityService cityService;

    @GetMapping()
    public ResponseEntity<Object> findAll() {
        try {
            List<Stadium> list = stadiumService.findAll();
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, list);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Integer id) {
        try {
            Stadium stadium = stadiumService.findById(id);
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, stadium);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @PostMapping("/{idCity}")
    public ResponseEntity<Object> save(@RequestBody Stadium stadium, @PathVariable Integer idCity) {
        try {
            City city = cityService.findById(idCity);
            if (city != null) {
                Stadium result = stadiumService.save(stadium, city);
                return ResponseHandler.generateResponse("Success", HttpStatus.OK, result);
            }
            return ResponseHandler.generateResponse("City Not Found",HttpStatus.NOT_FOUND, null );
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody Stadium stadium) {
        try {
            Stadium updatedStadium = stadiumService.update(id, stadium);
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, updatedStadium);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) {
        try {
            stadiumService.delete(id);
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, null);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    /*@GetMapping("/byCountry/{country}")
    public ResponseEntity<Object> findByCountry(@RequestBody String country) {
        try {
            List<Stadium> result = stadiumService.findByCountry(country);
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }*/

    @GetMapping("/byName/{name}")
    public ResponseEntity<Object> findByName(@PathVariable  String name){
        try {
            List<Stadium> result = stadiumService.findByName(name);
            return  ResponseHandler.generateResponse("Success",HttpStatus.OK, result);
        }catch (Exception e){
            return  ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
}
