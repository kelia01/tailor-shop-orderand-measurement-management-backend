package com.example.tailorShop.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.UUID;

@Entity 
@Table (name = "alteration")
public class Alteration {
    @Id 
    @GeneratedValue (strategy = GenerationType.UUID)
    private UUID id;

    String alterationType;
    String remarks;
    int centimetres;
    LocalDate fittingDate;

    @ManyToOne 
    @JoinColumn(name = "tailoring_order_id")
    TailoringOrder tailoringOrder;

    public UUID getId(){
        return id;
    }

    public String getAlterationType() {
        return alterationType;
    }

    public void setAlterationType(String alterationType) {
        this.alterationType = alterationType;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getCentimetres() {
        return centimetres;
    }

    public void setCentimetres(int centimetres) {
        this.centimetres = centimetres;
    }

    public LocalDate getFittingDate() {
        return fittingDate;
    }

    public void setFittingDate(LocalDate fittingDate) {
        this.fittingDate = fittingDate;
    }

    public TailoringOrder getTailoringOrder() {
        return tailoringOrder;
    }

    public void setTailoringOrder(TailoringOrder tailoringOrder) {
        this.tailoringOrder = tailoringOrder;
    }

}
