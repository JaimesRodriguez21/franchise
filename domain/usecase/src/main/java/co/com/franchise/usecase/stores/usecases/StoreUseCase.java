package co.com.franchise.usecase.stores.usecases;

import co.com.franchise.model.objects.ProductWithStore;
import co.com.franchise.model.product.Product;
import co.com.franchise.model.store.Store;
import co.com.franchise.model.store.gateways.StoreRepository;
import co.com.franchise.usecase.enums.ExceptionCodeEnum;
import co.com.franchise.usecase.exceptions.BusinessException;
import co.com.franchise.usecase.products.services.ProductService;
import co.com.franchise.usecase.stores.services.StoreService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RequiredArgsConstructor
public class StoreUseCase implements StoreService {
    private final StoreRepository storeRepository;
    private final ProductService productService;

    @Override
    public Mono<Store> createStore(Store store) {
        return this.validateUniqueStore(store.getName()).then(
                this.storeRepository.createStore(store));
    }

    @Override
    public Mono<Product> addProductToStore(String storeId, Product product) {
        return storeRepository.findById(storeId)
                .switchIfEmpty(Mono.error(new BusinessException(ExceptionCodeEnum.C01STOR02)))
                .flatMap(store -> {
                    product.setStoreId(storeId);
                    return productService.createProduct(product);
                });
    }

    @Override
    public Mono<Product> deleteProductFromStore(String storeId, String productId) {
        return storeRepository.findById(storeId)
                .switchIfEmpty(Mono.error(new BusinessException(ExceptionCodeEnum.C01STOR02)))
                .flatMap(store -> productService.deleteProductByIdAndStoreId(productId, store.getId()));
    }

    @Override
    public Flux<ProductWithStore> findMaxStockProductByFranchiseId(String franchiseId) {
        return storeRepository.findAllByFranchiseId(franchiseId)
                .flatMap(store -> productService.findProductMaxStock(store.getId())
                        .map(product -> new ProductWithStore(product, store)));
    }

    @Override
    public Mono<Store> updateStoreName(String storeId, String storeName) {
        return storeRepository.findById(storeId)
                .switchIfEmpty(Mono.error(new BusinessException(ExceptionCodeEnum.C01STOR02)))
                .flatMap(existingStore ->
                        storeRepository.findStoreByName(storeName)
                                .flatMap(foundStore -> {
                                    if (!foundStore.getId().equals(existingStore.getId())) {
                                        return Mono.error(new BusinessException(ExceptionCodeEnum.C01STOR03));
                                    }
                                    existingStore.setName(storeName);
                                    return storeRepository.updateStore(existingStore);
                                })
                                .switchIfEmpty(
                                        Mono.defer(() -> {
                                            existingStore.setName(storeName);
                                            return storeRepository.updateStore(existingStore);
                                        })
                                )
                );
    }

    private Mono<Void> validateUniqueStore(String name) {
        return storeRepository.findStoreByName(name.trim())
                .flatMap(franchise -> Mono.error(new BusinessException(ExceptionCodeEnum.C01STOR01)))
                .then();
    }
}
