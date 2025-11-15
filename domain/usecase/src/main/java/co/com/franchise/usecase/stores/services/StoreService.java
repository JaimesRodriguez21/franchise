package co.com.franchise.usecase.stores.services;

import co.com.franchise.model.product.Product;
import co.com.franchise.model.store.Store;
import reactor.core.publisher.Mono;

public interface StoreService {
    Mono<Store> createStore(Store store);
    Mono<Product> addProductToStore(String storeId, Product product);
    Mono<Product> deleteProductFromStore(String storeId, String productId);
    Mono<Product> updateProductStock(String storeId, String productId, int newStock);
}
