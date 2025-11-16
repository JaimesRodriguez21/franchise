package co.com.franchise.api.products;

import co.com.franchise.api.dtos.requests.products.UpdStockRequest;
import co.com.franchise.api.dtos.requests.utils.NameRequest;
import co.com.franchise.api.mapper.products.ProductMapper;
import co.com.franchise.api.validators.ValidationUtil;
import co.com.franchise.usecase.products.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ProductHandler {

    private final ProductService productService;
    private final ProductMapper productMapper;
    private final ValidationUtil validationUtil;

    public Mono<ServerResponse> updateProductStock(ServerRequest request) {
        String productId = request.pathVariable("productId");

        return validationUtil.validateBody(request.bodyToMono(UpdStockRequest.class))
                .flatMap(validationUtil::validate)
                .flatMap(req -> productService.updateProductStockById(productId, req.getStock()))
                .map(productMapper::toResponse)
                .flatMap(response ->
                        ServerResponse.ok().bodyValue(response)
                );
    }

    public Mono<ServerResponse> updateProductName(ServerRequest request) {
        String productId = request.pathVariable("productId");

        return validationUtil.validateBody(request.bodyToMono(NameRequest.class))
                .flatMap(validationUtil::validate)
                .flatMap(req -> productService.updateProductName(productId, req.getName()))
                .map(productMapper::toResponse)
                .flatMap(response -> ServerResponse.ok().bodyValue(response));
    }

}
