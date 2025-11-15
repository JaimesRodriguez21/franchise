package co.com.franchise.mongo.franchises;

import co.com.franchise.model.franchise.Franchise;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import reactor.core.publisher.Mono;

public interface FranchiseDBRepository extends ReactiveMongoRepository<FranchiseDocument, String>, ReactiveQueryByExampleExecutor<FranchiseDocument> {
    Mono<Franchise> findByNameIgnoreCase(String name);
}