package com.cognizant.transactionmicroservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "model class for amount")
public class AmountDTO {
    
    @JsonProperty
    @ApiModelProperty(value="long amount")
    private long amount;
}
