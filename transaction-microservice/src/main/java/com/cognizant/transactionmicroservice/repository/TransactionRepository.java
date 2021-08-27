package com.cognizant.transactionmicroservice.repository;

import com.cognizant.transactionmicroservice.model.TransactionModel;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionModel,Integer> {

    List<TransactionModel> findByUserName(String userName);
    
}
