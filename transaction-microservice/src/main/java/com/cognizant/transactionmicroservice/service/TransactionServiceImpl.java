package com.cognizant.transactionmicroservice.service;

import com.cognizant.transactionmicroservice.client.CustomerClient;
import com.cognizant.transactionmicroservice.dto.CustomerDetails;
import com.cognizant.transactionmicroservice.dto.TransactionDTO;
import com.cognizant.transactionmicroservice.model.TransactionModel;
import com.cognizant.transactionmicroservice.repository.TransactionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private CustomerClient customerClient;

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public boolean transferAmount(String token,CustomerDetails fromAc, TransactionDTO toAc) {
        
        try{
            boolean result=customerClient.withdrawAmount(token, new TransactionDTO(fromAc.getUserName(),toAc.getAmount())).getBody();
            if(result==true){
                String message1 ="Transferred to "+toAc.getUserName();
                TransactionModel transactionfrom=new TransactionModel(fromAc.getUserName(),fromAc.getUserName(),toAc.getUserName(),message1,"DEBIT",toAc.getAmount());
                transactionRepository.save(transactionfrom);
                boolean result2=customerClient.depositAmount(token, toAc).getBody();
                if(result2==true){
                    String message2="Recieved from "+fromAc.getUserName();
                    TransactionModel transactionTo=new TransactionModel(fromAc.getUserName(),fromAc.getUserName(),toAc.getUserName(),message2,"CREDIT",toAc.getAmount());
                    transactionRepository.save(transactionTo);
                    return true;
                }else{
                    customerClient.depositAmount(token, new TransactionDTO(fromAc.getUserName(),toAc.getAmount()));
                    String messageF1="Payment Failed...Amount Reversed";
                    TransactionModel transactionfromFailed=new TransactionModel(fromAc.getUserName(),"--",fromAc.getUserName(),messageF1,"CREDIT",toAc.getAmount());
                    transactionRepository.save(transactionfromFailed);
                    return false;
                }
            }else{
                return false;
            }

        }catch(Exception e){
            return false;
        }
    }

    @Override
    public boolean cashWithdraw(String token,TransactionDTO fromAc){
        try{
            boolean result=customerClient.withdrawAmount(token,fromAc).getBody();
            if(result==true){
                String message="Cash Withdrawal";
                TransactionModel transactionWithdraw=new TransactionModel(fromAc.getUserName(),fromAc.getUserName(),"--",message,"DEBIT",fromAc.getAmount());  
                transactionRepository.save(transactionWithdraw);  
                return true;           
            }
            else{
                return false;
            }
        }
        catch(Exception e){
            return false;
        }
    }

    @Override
    public boolean depositCash(String token,TransactionDTO toAc){
        boolean result = customerClient.depositAmount(token,toAc).getBody();
        if(result == true){
            String message ="Cash Deposit";
            TransactionModel transactionDeposit = new TransactionModel(toAc.getUserName(),"--",toAc.getUserName(),message,"CREDIT",toAc.getAmount());
            transactionRepository.save(transactionDeposit);
            return true;
        }
        else{
            return false;
        }

    }
    
}
