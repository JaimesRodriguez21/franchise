package co.com.franchise.usecase.products.usecases;

import co.com.franchise.model.product.Product;
import co.com.franchise.model.product.gateways.ProductRepository;
import co.com.franchise.usecase.enums.ExceptionCodeEnum;
import co.com.franchise.usecase.exceptions.BusinessException;
import co.com.franchise.usecase.products.services.ProductService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;


@RequiredArgsConstructor
public class ProductUseCase implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Mono<Product> createProduct(Product product) {
        return this.validateUniqueProduct(product.getName()).then(
                this.productRepository.createProduct(product));
    }

    @Override
    public Mono<Product> deleteProductByIdAndStoreId(String storeId, String productId) {
        return productRepository.findProductByIdAndStoreId(productId, storeId)
                .switchIfEmpty(Mono.error(new BusinessException(ExceptionCodeEnum.C01PDTS02)))
                .flatMap(product ->
                        productRepository.deleteProductProduct(product)
                                .thenReturn(product)
                );
    }

    @Override
    public Mono<Product> updateProductStockById(String productId, int newStock) {
        return productRepository.findById(productId)
                .switchIfEmpty(Mono.error(new BusinessException(ExceptionCodeEnum.C01PDTS02)))
                .flatMap(product -> {
                    product.setStock(newStock);
                    return productRepository.updateProduct(product);
                });
    }

    @Override
    public Mono<Product> findProductMaxStock(String storeId) {
        return productRepository.findMaxStockByStoreId(storeId);
    }

    private Mono<Void> validateUniqueProduct(String name) {
        return productRepository.findProductByName(name)
                .flatMap(product -> Mono.error(new BusinessException(ExceptionCodeEnum.C01PDTS01)))
                .then();
    }
}
