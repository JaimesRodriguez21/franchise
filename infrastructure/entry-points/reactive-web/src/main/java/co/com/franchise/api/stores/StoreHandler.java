package co.com.franchise.api.stores;

import co.com.franchise.api.dtos.requests.products.ProductRequest;
import co.com.franchise.api.mapper.products.ProductMapper;
import co.com.franchise.usecase.stores.services.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class StoreHandler {

    private final StoreService storeService;
    private final ProductMapper productMapper;

    public Mono<ServerResponse> addProductToStore(ServerRequest request) {
        String storeId = request.pathVariable("storeId");

        return request.bodyToMono(ProductRequest.class)
                .map(productMapper::toDomain)
                .flatMap(product -> storeService.addProductToStore(storeId, product))
                .map(productMapper::toResponse)
                .flatMap(response ->
                        ServerResponse.status(HttpStatus.CREATED)
                                .bodyValue(response)
                );
    }
}
