package com.induscollege.ic_auth_service.models;


import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;


/**
 * UserResponse
 */
@Builder
@Data
public class UserResponse{
    
    private String name;
    private String email;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String token;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date tokenExpiration;
}