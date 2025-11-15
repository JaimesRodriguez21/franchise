package co.com.franchise.api.dtos.responses.products;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class ProductDetailResponse {

    private String id;
    private String name;
    private int stock;
    private String storeId;
    private String storeName;
}