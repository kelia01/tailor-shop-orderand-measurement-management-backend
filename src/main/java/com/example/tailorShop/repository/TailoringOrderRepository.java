package com.example.tailorShop.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.tailorShop.domain.OrderStatus;
import com.example.tailorShop.domain.TailoringOrder;

@Repository 
public interface TailoringOrderRepository extends JpaRepository<TailoringOrder, UUID>{
    List<TailoringOrder> findByOrderStatusOrderByOrderDateAsc(OrderStatus orderStatus);
    List<TailoringOrder> findByOrderDateBetween(LocalDate start, LocalDate end);

    boolean existsByTailor_IdAndOrderDateAndStatusNot(
        UUID tailorId, LocalDate orderDate, OrderStatus status);

    @Query ("""
        SELECT o.status, COUNT(o)
        FROM TailoringOrder o
        GROUP BY orderStatus
        """)

    List<Object[]> countOrdersByStatus();
    
    @Modifying
    @Transactional 
    @Query ("""
        UPDATE TailoringOrder o 
        SET o.orderStatus = :newStatus
        WHERE o.tailorId = :tailorId
            AND o.orderDate = :date
            AND o.status <> :excludedStatus
        """)

    int cancelOrdersForTailorOnDate(
        @Param("tailorId") UUID tailorId,
        @Param("date") LocalDate date,
        @Param("newStatus") OrderStatus newStatus,
        @Param("excludedStatus") OrderStatus excludedStatus
    );
}
