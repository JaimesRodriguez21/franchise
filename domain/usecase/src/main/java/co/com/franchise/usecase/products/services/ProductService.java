package co.com.franchise.usecase.products.services;

import co.com.franchise.model.product.Product;
import reactor.core.publisher.Mono;

public interface ProductService {
    Mono<Product> createProduct(Product product);
    Mono<Product> deleteProductByIdAndStoreId(String storeId, String productId);
    Mono<Product> updateProductByIdAndStoreId(String productId, String storeId, int newStock);
}
