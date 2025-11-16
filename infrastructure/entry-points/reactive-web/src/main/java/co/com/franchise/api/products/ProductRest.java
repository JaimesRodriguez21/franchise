package co.com.franchise.api.products;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Component
@Configuration
public class ProductRest {

    @Bean
    public RouterFunction<ServerResponse> productRoutes(ProductHandler handler) {
        return route()
                .nest(path("/products"), builder -> builder
                        .PUT("/{productId}/name", handler::updateProductName)
                        .PUT("/{productId}/stock", handler::updateProductStock))
                .build();
    }
}
