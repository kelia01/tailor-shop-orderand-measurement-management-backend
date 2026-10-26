package com.example.tailorShop.domain;

import jakarta.persistence.*;
import jakarta.persistence.Table;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity 
@Table (name = "garment_type")
public class GarmentType {
    

    @Id 
    @GeneratedValue (strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @ManyToMany(mappedBy = "garmentTypes")
    @JsonIgnore
    private List<Tailor> tailors = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Tailor> getTailors() {
        return tailors;
    }

    public void setTailors(List<Tailor> tailors) {
        this.tailors = tailors;
    }

    public UUID getId(){
        return id;
    }

}
