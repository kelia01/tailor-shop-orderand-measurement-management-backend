package com.example.tailorShop.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.tailorShop.domain.Alteration;

@Repository 
public interface AlterationRepository extends JpaRepository<Alteration, UUID> {
    
}
