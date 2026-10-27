package com.example.tailorShop.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.tailorShop.domain.Alteration;
import com.example.tailorShop.domain.TailoringOrder;

@Repository 
public interface AlterationRepository extends JpaRepository<Alteration, UUID> {
    
    @Query ("""
        SELECT a
        FROM Alteration a
        WHERE a.tailoringOrder.customer.customerCode = :customerCode
        ORDER BY a.fittingDate DESC
        """)

    List<Alteration> findByCustomerCode(
        @Param ("customerCode") String customerCode
    );

    boolean existsByTailoringOrder(TailoringOrder order);
}
