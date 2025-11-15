package co.com.franchise.model.store.gateways;

import co.com.franchise.model.store.Store;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StoreRepository {
    Mono<Store> createStore(Store store);
    Mono<Store> findById(String id);
    Mono<Store> findStoreByName(String name);
    Flux<Store> findAllByFranchiseId(String id);

}
