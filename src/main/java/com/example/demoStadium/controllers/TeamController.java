package com.example.demoStadium.controllers;

import com.example.demoStadium.entities.Stadium;
import com.example.demoStadium.entities.Team;
import com.example.demoStadium.response.ResponseHandler;
import com.example.demoStadium.services.StadiumService;
import com.example.demoStadium.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/team")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @Autowired
    private StadiumService stadiumService;

    @GetMapping
    public ResponseEntity<Object> findAll() {
        try {
            List<Team> result = teamService.findAll();
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Integer id) {
        try {
            Team result = teamService.findById(id);
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @PostMapping("/{idStadium}")
    public ResponseEntity<Object> save(@RequestBody Team team, @PathVariable Integer idStadium){
        try {
            Stadium stadium = stadiumService.findById(idStadium);
            if (stadium!=null) {
                Team result = teamService.save(team, stadium);
                return ResponseHandler.generateResponse("Success", HttpStatus.CREATED, result);
            }
            return ResponseHandler.generateResponse("Stadium Not Found",HttpStatus.NOT_FOUND, null);
        }catch (Exception e){
            return  ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody Team updatedTeam) {
        try {
            Team result = teamService.update(id, updatedTeam);
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id){
        try{
            Team result = teamService.delete(id);
            return ResponseHandler.generateResponse("Succes",HttpStatus.OK, result);
        }catch( Exception e ){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

}
