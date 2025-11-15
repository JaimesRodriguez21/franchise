package co.com.franchise.api.dtos.responses.products;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class ProductResponse {
    private String id;
    private String name;
    private String storeId;
    private int stock;
}
