package com.cognizant.customermicroservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Entity
@Table(name="customer")
@ApiModel(description="model class for audit details")
public class CustomerDetails {

    @Id
    @Column(name="username")
    @ApiModelProperty(value="unique user name of customer")
    private String userName;

    @ApiModelProperty(value="name name of customer")
    private String customerName;

    @ApiModelProperty(value="account number of customer")
    private String accountNumber;

    @ApiModelProperty(value="balance of customer")
    private long accountBalance;

    
}
