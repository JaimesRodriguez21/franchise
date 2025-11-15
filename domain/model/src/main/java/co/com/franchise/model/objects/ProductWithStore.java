package co.com.franchise.model.objects;

import co.com.franchise.model.product.Product;
import co.com.franchise.model.store.Store;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductWithStore {
    private Product product;
    private Store store;

    public ProductWithStore(Product product, Store store) {
        this.product = product;
        this.store = store;
    }
}
