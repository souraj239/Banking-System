package com.cognizant.transactionmicroservice.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description="model class for audit details")
public class CustomerDetails {
    /**
	 * Gets the customer details from feign client
	 * @param 
	 * @return 
	 */
    @ApiModelProperty(value="unique user name of customer")
    private String userName;

    @ApiModelProperty(value="name name of customer")
    private String customerName;

    @ApiModelProperty(value="account number of customer")
    private String accountNumber;

    @ApiModelProperty(value="balance of customer")
    private long accountBalance;

    
}
