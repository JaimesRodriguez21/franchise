package co.com.franchise.usecase.products.services;

import co.com.franchise.model.product.Product;
import reactor.core.publisher.Mono;

public interface ProductService {
    Mono<Product> createProduct(Product product);
    Mono<Product> deleteProductByIdAndStoreId(String storeId, String productId);
    Mono<Product> updateProductStockById(String productId, int newStock);
    Mono<Product> findProductMaxStock(String storeId);
    Mono<Product> updateProductName(String productId, String name);
}
