package co.com.franchise.mongo;

import co.com.franchise.model.franchise.Franchise;
import co.com.franchise.mongo.documents.FranchiseDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import reactor.core.publisher.Mono;

public interface MongoDBRepository extends ReactiveMongoRepository<FranchiseDocument, String>, ReactiveQueryByExampleExecutor<FranchiseDocument> {
    Mono<Franchise> findByNameIgnoreCase(String name);
}