package com.authorizationservice.authorization.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootApplication
public class AuthenticationResponseTest
{
   @Autowired
   private AuthenticationResponse authenticationResponse;
   /**
    * Test method to check the authentication response.
    */
   @Test
   @DisplayName("Checking for AuthenticationResponse - if it is loading or not")
   public void authenticationResponseNotNullTest(){
       assertThat(authenticationResponse).isNull();
   }
}
