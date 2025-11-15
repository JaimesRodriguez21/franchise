package co.com.franchise.api.mapper.products;

import co.com.franchise.api.dtos.requests.products.ProductRequest;
import co.com.franchise.api.dtos.responses.products.ProductDetailResponse;
import co.com.franchise.api.dtos.responses.products.ProductResponse;
import co.com.franchise.model.objects.ProductWithStore;
import co.com.franchise.model.product.Product;
import co.com.franchise.model.store.Store;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public Product toDomain(ProductRequest request) {
        return Product.builder()
                .name(request.getName())
                .stock(request.getStock())
                .build();
    }

    public ProductResponse toResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .stock(product.getStock())
                .storeId(product.getStoreId())
                .build();
    }

    public ProductDetailResponse toResponseDetail(ProductWithStore productWithStore) {
        Product product = productWithStore.getProduct();
        Store store = productWithStore.getStore();
        return ProductDetailResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .stock(product.getStock())
                .storeId(product.getStoreId())
                .storeName(store.getName())
                .build();
    }
}
