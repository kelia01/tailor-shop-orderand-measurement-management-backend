package com.example.tailorShop.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.tailorShop.domain.OrderStatus;
import com.example.tailorShop.domain.TailoringOrder;

@Repository 
public interface TailoringOrderRepository extends JpaRepository<TailoringOrder, UUID>{
    List<TailoringOrder> findByOrderStatusOrderByOrderDateAsc(OrderStatus orderStatus);
    List<TailoringOrder> findByOrderDateBetween(LocalDate start, LocalDate end);

    boolean existsByTailor_IdAndOrderDateAndStatusNot(
        UUID tailorId, LocalDate orderDate, OrderStatus status);

    
}
