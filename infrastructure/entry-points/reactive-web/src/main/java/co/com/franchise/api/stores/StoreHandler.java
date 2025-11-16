package co.com.franchise.api.stores;

import co.com.franchise.api.dtos.requests.products.ProductRequest;
import co.com.franchise.api.dtos.requests.utils.NameRequest;
import co.com.franchise.api.mapper.products.ProductMapper;
import co.com.franchise.api.mapper.stores.StoreMapper;
import co.com.franchise.api.validators.ValidationUtil;
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
    private final StoreMapper storeMapper;
    private final ValidationUtil validationUtil;

    public Mono<ServerResponse> addProductToStore(ServerRequest request) {
        String storeId = request.pathVariable("storeId");

        return validationUtil.validateBody(request.bodyToMono(ProductRequest.class))
                .map(productMapper::toDomain)
                .flatMap(validationUtil::validate)
                .flatMap(product -> storeService.addProductToStore(storeId, product))
                .map(productMapper::toResponse)
                .flatMap(response ->
                        ServerResponse.status(HttpStatus.CREATED)
                                .bodyValue(response)
                );
    }

    public Mono<ServerResponse> deleteProductFromStore(ServerRequest request) {
        String storeId = request.pathVariable("storeId");
        String productId = request.pathVariable("productId");

        return storeService.deleteProductFromStore(storeId, productId)
                .map(productMapper::toResponse)
                .flatMap(response ->
                        ServerResponse.ok()
                                .bodyValue(response)
                );
    }

    public Mono<ServerResponse> updateStoreName(ServerRequest request) {
        String storeId = request.pathVariable("storeId");

        return validationUtil.validateBody(request.bodyToMono(NameRequest.class))
                .flatMap(validationUtil::validate)
                .flatMap(req -> storeService.updateStoreName(storeId, req.getName()))
                .map(storeMapper::toResponse)
                .flatMap(response -> ServerResponse.ok().bodyValue(response));
    }

}
