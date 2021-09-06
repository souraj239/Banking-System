package com.cognizant.transactionmicroservice.dto;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Api(description = "model class to take arguments as string and change t to double")
public class TempTransactionDTO {
    
    private String userName;

    private String amount;

    public long getChangedType() {
        return Long.parseLong(this.amount);
    }

    public TransactionDTO getConverted(){
        return new TransactionDTO(this.userName, this.getChangedType());
    }
}
