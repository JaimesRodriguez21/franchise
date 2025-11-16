package co.com.franchise.api.dtos.requests.products;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductRequest {
    @NotBlank(message = "The name cannot be blank or null")
    private String name;
    @NotNull(message = "The stock cannot be null")
    private Integer stock;
}
