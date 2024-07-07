package com.induscollege.api_gateway.predicates;

import java.util.Optional;

import org.springframework.web.client.RestClient;
import org.springframework.web.servlet.function.RequestPredicate;

import com.induscollege.api_gateway.utility.Utility;

public class CustomPredicates {

  public static RequestPredicate isJWTTokenValid() {

    return request -> {

      final String token = Utility.extractJWTToken(request);

      RestClient restclient = RestClient.builder().baseUrl("http://auth-service:8080").build();

      Boolean response = restclient.get().uri(uribuilder -> uribuilder.path("/public/user/validateToken")
          .queryParam("token", token).build())
          .retrieve().body(Boolean.class);

      return Optional.of(response).orElse(false);

    };
  }


}
