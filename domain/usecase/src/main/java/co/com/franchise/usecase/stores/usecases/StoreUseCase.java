package co.com.franchise.usecase.stores.usecases;

import co.com.franchise.model.product.Product;
import co.com.franchise.model.store.Store;
import co.com.franchise.model.store.gateways.StoreRepository;
import co.com.franchise.usecase.enums.ExceptionCodeEnum;
import co.com.franchise.usecase.exceptions.BusinessException;
import co.com.franchise.usecase.products.services.ProductService;
import co.com.franchise.usecase.stores.services.StoreService;
import lombok.RequiredArgsConstructor;
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
                .flatMap(product -> productService.deleteProductByIdAndStoreId(productId, storeId));
    }

    private Mono<Void> validateUniqueStore(String name) {
        return storeRepository.findStoreByName(name)
                .flatMap(franchise -> Mono.error(new BusinessException(ExceptionCodeEnum.C01STOR01)))
                .then();
    }
}
