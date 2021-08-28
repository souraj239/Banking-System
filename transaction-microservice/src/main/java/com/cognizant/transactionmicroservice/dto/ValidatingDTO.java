package com.cognizant.transactionmicroservice.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "model class feign client")
public class ValidatingDTO {
	/**
	 * It is to validate the token
	 */
    @JsonProperty
	private boolean validStatus;

    @JsonProperty
    private String userName="";
    

}
