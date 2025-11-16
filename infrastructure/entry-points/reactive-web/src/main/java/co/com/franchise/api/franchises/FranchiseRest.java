package co.com.franchise.api.franchises;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class FranchiseRest {
    @Bean
    public RouterFunction<ServerResponse> franchiseRoutes(FranchiseHandler handler) {
        return route()
                .nest(path("/franchises"), builder -> builder
                        .POST("", handler::createFranchise)
                        .POST("/{franchiseId}/stores", handler::addStoreToFranchise)
                        .PUT("/{franchiseId}", handler::updateFranchiseName)
                        .GET("/{franchiseId}", handler::getMaxStockProductsByFranchise)
                )
                .build();
    }
}
