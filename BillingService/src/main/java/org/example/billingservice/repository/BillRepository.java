package org.example.billingservice.repository;

import org.example.billingservice.entities.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;
import org.springframework.data.repository.query.Param;

public interface BillRepository extends JpaRepository<Bill, Long>{
    @RestResource(path = "byCustomerId")
    List<Bill> findByCustomerId(@Param("customerId") Long customerId);
}
