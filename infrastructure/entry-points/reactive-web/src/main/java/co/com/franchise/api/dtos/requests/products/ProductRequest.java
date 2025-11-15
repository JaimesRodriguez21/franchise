package co.com.franchise.api.dtos.requests.products;

import lombok.Data;

@Data
public class ProductRequest {
    private String name;
    private int stock;
}
