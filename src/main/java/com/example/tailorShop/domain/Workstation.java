package com.example.tailorShop.domain;

import jakarta.persistence.*;
import java.util.*;

@Entity 
@Table (name= "workstation")
public class Workstation {

    @Id 
    @GeneratedValue (strategy = GenerationType.UUID)
    private UUID id;
    private int stationNumber;
    private String name;

    public UUID getId(){
        return id;
    }

    public int getStationNumber() {
        return stationNumber;
    }

    public void setStationNumber(int stationNumber) {
        this.stationNumber = stationNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
