package co.com.franchise.mongo.products;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import reactor.core.publisher.Mono;

public interface ProductDBRepository extends ReactiveMongoRepository<ProductDocument, String>, ReactiveQueryByExampleExecutor<ProductDocument> {
    Mono<ProductDocument> findByNameIgnoreCase(String name);

    Mono<ProductDocument> findProductDocumentByIdAndStoreId(String storeId, String productId);

    @Aggregation(pipeline = {
            "{ $match: { storeId: ?0 } }",
            "{ $sort: { stock: -1 } }",
            "{ $limit: 1 }"
    })
    Mono<ProductDocument> findMaxStockByStoreId(String storeId);
}