package com.example.tailorShop.domain;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "tailor")
public class Tailor {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String firstName;
    private String lastName;
    private String phone;

    @ManyToOne 
    @JoinColumn (name = "workstation_id", nullable=true)
    private Workstation workstation;

    @ManyToMany 
    @JoinTable (
        name = "tailor_garment_type",
        joinColumns =  @JoinColumn (name = "tailor_id"),
        inverseJoinColumns = @JoinColumn (name = "garment_type_id")
    )
    private List<GarmentType> garmentTypes = new ArrayList<>();

    public UUID getId() { return id; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public Workstation getWorkstation() {
        return workstation;
    }
    public void setWorkstation(Workstation workstation) {
        this.workstation = workstation;
    }
    public List<GarmentType> getGarmentTypes() {
        return garmentTypes;
    }
    public void setGarmentTypes(List<GarmentType> garmentTypes) {
        this.garmentTypes = garmentTypes;
    }
}
