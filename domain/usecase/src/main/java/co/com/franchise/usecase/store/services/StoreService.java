package co.com.franchise.usecase.store.services;

import co.com.franchise.model.store.Store;
import reactor.core.publisher.Mono;

public interface StoreService {
    Mono<Store> createStore(Store store);

}
