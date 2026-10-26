package com.example.tailorShop.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.*;

@Entity 
@Table (name = "tailoring_order")
public class TailoringOrder {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private LocalDate orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    
    private String garmentDescription;

    private int chestCm;

    @ManyToOne 
    @JoinColumn (name = "customer_id")
    private Customer customer;

    @ManyToOne 
    @JoinColumn(name = "tailor_id")
    private Tailor tailor;

    public UUID getId(){
        return id;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getGarmentDescription() {
        return garmentDescription;
    }

    public void setGarmentDescription(String garmentDescription) {
        this.garmentDescription = garmentDescription;
    }

    public int getChestCm() {
        return chestCm;
    }

    public void setChestCm(int chestCm) {
        this.chestCm = chestCm;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Tailor getTailor() {
        return tailor;
    }

    public void setTailor(Tailor tailor) {
        this.tailor = tailor;
    }
    
}
