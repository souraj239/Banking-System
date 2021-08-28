package com.cognizant.transactionmicroservice.repository;

import com.cognizant.transactionmicroservice.model.TransactionModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionModel,Integer> {
    
}
