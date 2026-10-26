package com.example.tailorShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.tailorShop.domain.Tailor;

import java.util.UUID;

@Repository
public interface TailorRepository extends JpaRepository<Tailor, UUID> {
    boolean existsByWorkstation_Id(UUID workstation);
    boolean existsByWorkstation_StationNumber(int stationNumber);
}