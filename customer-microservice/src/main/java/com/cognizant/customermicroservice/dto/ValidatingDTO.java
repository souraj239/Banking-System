package com.cognizant.customermicroservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ValidatingDTO {
	/**
	 * It is to validate the token
	 */
    @JsonProperty
	private boolean validStatus;

    @JsonProperty
    private String userName="";
    

}
