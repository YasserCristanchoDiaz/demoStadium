package com.example.demoStadium.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "team")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false)
    private LocalDate dateFoundation;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Confederation confederation;

    @OneToOne
    @JoinColumn(name = "stadium_id")
    private Stadium stadium;

    public Team() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Confederation getConfederation() {
        return confederation;
    }

    public void setConfederation(Confederation confederation) {
        this.confederation = confederation;
    }

    public LocalDate getDateFoundation() {
        return dateFoundation;
    }

    public void setDateFoundation(LocalDate dateFoundation) {
        this.dateFoundation = dateFoundation;
    }

    public Stadium getStadium() {
        return stadium;
    }

    public void setStadium(Stadium stadium) {
        this.stadium = stadium;
    }
}
