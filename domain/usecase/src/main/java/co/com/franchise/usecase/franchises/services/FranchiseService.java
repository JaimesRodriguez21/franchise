package co.com.franchise.usecase.franchises.services;

import co.com.franchise.model.franchise.Franchise;
import co.com.franchise.model.store.Store;
import reactor.core.publisher.Mono;

public interface FranchiseService {
    Mono<Franchise> createFranchise(Franchise franchise);
    Mono<Store> addStoreToFranchise(String franchiseId, Store store);
}
