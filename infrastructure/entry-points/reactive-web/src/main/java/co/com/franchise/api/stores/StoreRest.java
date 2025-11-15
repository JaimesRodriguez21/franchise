package co.com.franchise.api.stores;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Component
@Configuration
public class StoreRest {

    @Bean
    public RouterFunction<ServerResponse> storeRoutes(StoreHandler handler) {
        return route()
                .POST("/stores/{storeId}/products", handler::addProductToStore)
                .build();
    }
}
