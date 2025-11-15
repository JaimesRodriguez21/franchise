package co.com.franchise.model.franchise.gateways;

import co.com.franchise.model.franchise.Franchise;
import reactor.core.publisher.Mono;

public interface FranchiseRepository {
    Mono<Franchise> createFranchise(Franchise franchise);
    Mono<Franchise> findFranchiseByName(String name);
    Mono<Franchise> findById(String id);

}
