package co.com.franchise.mongo.stores;


import co.com.franchise.model.store.Store;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import reactor.core.publisher.Mono;

public interface StoreDBRepository extends ReactiveMongoRepository<StoreDocument, String>, ReactiveQueryByExampleExecutor<StoreDocument> {
    Mono<Store> findByNameIgnoreCase(String name);
}
