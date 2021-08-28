package com.cognizant.transactionmicroservice.repository;

import com.cognizant.transactionmicroservice.model.TransactionModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionModel,Integer> {

    List<TransactionModel> findByUserName(String username);
    
}
