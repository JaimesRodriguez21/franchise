package co.com.franchise.mongo.stores;


import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StoreDBRepository extends ReactiveMongoRepository<StoreDocument, String>, ReactiveQueryByExampleExecutor<StoreDocument> {
    Mono<StoreDocument> findByNameIgnoreCase(String name);

    Flux<StoreDocument> findAllByFranchiseId(String franchiseId);
}
