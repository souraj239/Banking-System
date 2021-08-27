package com.cognizant.transactionmicroservice.auth;

import io.swagger.annotations.Api;

import com.cognizant.transactionmicroservice.dto.ValidatingDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;


@FeignClient(name = "auth-client", url = "http://localhost:8008/authorization")
@Api(value="validating authentication")
public interface AuthClient {
	 
	/**
	 * This method is to validate the user token.
	 * @param token
	 * @return
	 */
	@GetMapping(value = "/validate")
	public ValidatingDTO getsValidity(@RequestHeader(name="Authorization",required=true) String token) ;


}

	
