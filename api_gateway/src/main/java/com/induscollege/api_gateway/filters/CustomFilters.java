package com.induscollege.api_gateway.filters;


import java.util.function.Function;

import org.springframework.web.servlet.function.ServerRequest;

import com.induscollege.api_gateway.utility.Utility;

public class CustomFilters {

    	public static Function<ServerRequest, ServerRequest> addUserIdHeader() {
		return request -> {
			// remove resolved params from request
			return ServerRequest.from(request).header("userId", Utility.extractUserIdFromJWT(request)).build();
		};
	}
}
