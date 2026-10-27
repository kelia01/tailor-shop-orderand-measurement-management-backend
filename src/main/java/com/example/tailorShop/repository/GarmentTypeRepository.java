package com.example.tailorShop.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.tailorShop.domain.GarmentType;

@Repository 
public interface GarmentTypeRepository extends JpaRepository<GarmentType, UUID> {
    
    @Query ("""
        SELECT g
        FROM GarmentType g
        WHERE g.tailors IS EMPTY
        """)
    List<GarmentType> findUnusedGarmentTypes();
}
