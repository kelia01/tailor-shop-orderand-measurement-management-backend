package com.example.tailorShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.tailorShop.domain.Customer;

import java.util.List;
import java.util.UUID;

@Repository 
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
        boolean existsByCustomerCode(String customerCode);
        List<Customer> findByLastNameIgnoreCaseOrderByFirstNameAsc(String lastName);

        @Query ("""
                SELECT DISTINCT o.customer
                FROM TailoringOrder o
                WHERE o.tailor.id = :tailorId
                """)

        List<Customer> findCustomersOfTailor(@Param("tailorId") UUID tailorId );
}