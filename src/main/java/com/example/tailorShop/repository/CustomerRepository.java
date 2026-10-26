package com.example.tailorShop.repository;

import com.example.tailorShop.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository 
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
        boolean existsByCustomerCode(String customerCode);
}