package com.cognizant.transactionmicroservice.service;

import java.util.List;

import com.cognizant.transactionmicroservice.dto.CustomerDetails;
import com.cognizant.transactionmicroservice.dto.StatementDTO;

import org.springframework.stereotype.Service;

@Service
public interface TransactionRecordService {

    public List<StatementDTO> getStatement(CustomerDetails customer);
    
}
