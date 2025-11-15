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

    private Mono<Void> validateUniqueProduct(String name) {
        return productRepository.findProductByName(name)
                .flatMap(franchise -> Mono.error(new BusinessException(ExceptionCodeEnum.C01PDTS01)))
                .then();
    }
}
