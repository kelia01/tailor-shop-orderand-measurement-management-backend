package com.example.tailorShop.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.tailorShop.domain.Workstation;

@Repository
public interface WorkstationRepository extends JpaRepository<Workstation, UUID> {
    boolean existsByStationNumber(int stationNumber);
    Optional<Workstation> findByStationNumber(int stationNumber);

    @Query ("""
        SELECT w
        FROM Workstation w
        WHERE NOT EXISTS(
         SELECT t FROM Tailor t 
         WHERE t.workstation = w
        ) ORDERBY w.stationNumber ASC

        """)

    List<Workstation> findFreeWorkstations();
}
