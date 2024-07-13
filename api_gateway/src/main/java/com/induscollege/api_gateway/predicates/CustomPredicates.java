package com.induscollege.api_gateway.predicates;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.servlet.function.RequestPredicate;

import com.induscollege.api_gateway.utility.Utility;

@Component
public class CustomPredicates {

  @Value("${auth_service}")
  private String auth_service;
  
  public RequestPredicate isJWTTokenValid() {

    return request -> {

      final String token = Utility.extractJWTToken(request);

      RestClient restclient = RestClient.builder().baseUrl(auth_service).build();

      Boolean response = restclient.get().uri(uribuilder -> uribuilder.path("/public/user/validateToken")
          .queryParam("token", token).build())
          .retrieve().body(Boolean.class);

      return Optional.of(response).orElse(false);

    };
  }


}
