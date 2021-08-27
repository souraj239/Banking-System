package com.cognizant.customermicroservice.repository;

import com.cognizant.customermicroservice.model.CustomerDetails;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerDetails, String> {

    CustomerDetails findByUserName(String username);
    
}
