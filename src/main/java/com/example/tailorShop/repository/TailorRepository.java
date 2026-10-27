package com.example.tailorShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.tailorShop.domain.Tailor;

import java.util.List;
import java.util.UUID;

@Repository
public interface TailorRepository extends JpaRepository<Tailor, UUID> {
    boolean existsByWorkstation_Id(UUID workstation);
    boolean existsByWorkstation_StationNumber(int stationNumber);

    @Query("""
        SELECT t 
        FROM Tailor t
        JOIN t.garmentTypes g
        WHERE LOWER(g.name) = LOWER(:name)
        """)

    List<Tailor> findByGarmentTypeName(@Param ("name") String name);
    
}