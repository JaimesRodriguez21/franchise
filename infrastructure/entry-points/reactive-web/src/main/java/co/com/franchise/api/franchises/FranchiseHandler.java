package co.com.franchise.api.franchises;

import co.com.franchise.api.dtos.requests.franchises.FranchiseRequest;
import co.com.franchise.api.dtos.requests.stores.StoreRequest;
import co.com.franchise.api.mapper.franchises.FranchiseMapper;
import co.com.franchise.api.mapper.stores.StoreMapper;
import co.com.franchise.usecase.franchises.services.FranchiseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class FranchiseHandler {
    private final FranchiseService franchiseService;
    private final FranchiseMapper franchiseMapper;
    private final StoreMapper storeMapper;

    public Mono<ServerResponse> createFranchise(ServerRequest request) {
        return request.bodyToMono(FranchiseRequest.class)
                .map(franchiseMapper::toDomain)
                .flatMap(franchiseService::createFranchise)
                .map(franchiseMapper::toResponse)
                .flatMap(response ->
                        ServerResponse.status(HttpStatus.CREATED)
                                .bodyValue(response)
                );
    }

    public Mono<ServerResponse> addStoreToFranchise(ServerRequest request) {
        String franchiseId = request.pathVariable("franchiseId");

        return request.bodyToMono(StoreRequest.class)
                .map(storeMapper::toDomain)
                .flatMap(store -> franchiseService.addStoreToFranchise(franchiseId, store))
                .map(storeMapper::toResponse)
                .flatMap(response ->
                        ServerResponse.status(HttpStatus.CREATED)
                                .bodyValue(response)
                );
    }


}
