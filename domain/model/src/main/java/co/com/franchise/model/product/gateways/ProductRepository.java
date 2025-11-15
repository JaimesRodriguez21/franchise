package co.com.franchise.model.product.gateways;

import co.com.franchise.model.product.Product;
import reactor.core.publisher.Mono;

public interface ProductRepository {
    Mono<Product> createProduct(Product product);
    Mono<Product>  updateProduct(Product product);
    Mono<Product> findProductByName(String name);
    Mono<Void> deleteProductProduct(Product product);
    Mono<Product> findProductByIdAndStoreId(String storeId, String productId);

}
