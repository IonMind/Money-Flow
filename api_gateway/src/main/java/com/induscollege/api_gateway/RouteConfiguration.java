package com.induscollege.api_gateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.HandlerFunction;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import com.induscollege.api_gateway.filters.CustomFilters;

import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;

import static com.induscollege.api_gateway.predicates.CustomPredicates.isJWTTokenValid;
import static org.springframework.cloud.gateway.server.mvc.predicate.GatewayRequestPredicates.path;


@Configuration
public class RouteConfiguration {
    // @Value("${auth_service}")
    private String auth_service = System.getenv("auth_service"); //"http://auth-service"; 

    // @Value("${expense_manager_service}")
    private String expense_manager_service = System.getenv("expense_manager_service");
    
    @Bean
    public RouterFunction<ServerResponse> gatewayRouterFunctionPath() {
        HandlerFunction<ServerResponse> base_public_handlerfun = HandlerFunctions.http(auth_service);  //no auth required
        HandlerFunction<ServerResponse> base_expense_manager_handlerfun = HandlerFunctions.http(expense_manager_service);
        String public_paths[] = { "/public/user/register", "/public/user/login" };
        String expense_manager_paths[] = {"/expense/**"};
        

        return GatewayRouterFunctions.route("register_login")
                .route(path(public_paths), base_public_handlerfun)
                .build()
                .and(
                        GatewayRouterFunctions.route("expense_manager")
                                .route(isJWTTokenValid().and(path(expense_manager_paths)), base_expense_manager_handlerfun)
                                .before(CustomFilters.addUserIdHeader())
                                .build());
    }
}
