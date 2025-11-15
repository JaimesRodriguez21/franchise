package co.com.franchise.api;

import co.com.franchise.api.dtos.requests.franchises.FranchiseRequest;
import co.com.franchise.api.mapper.FranchiseMapper;
import co.com.franchise.usecase.franchise.services.FranchiseService;
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
}
