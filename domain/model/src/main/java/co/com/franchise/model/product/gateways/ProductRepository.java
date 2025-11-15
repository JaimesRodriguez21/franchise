package co.com.franchise.model.product.gateways;

import co.com.franchise.model.product.Product;
import reactor.core.publisher.Mono;

public interface ProductRepository {
    Mono<Product> createProduct(Product product);

    Mono<Product> findProductByName(String name);

}
