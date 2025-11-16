package co.com.franchise.api.dtos.requests.products;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdStockRequest {
    @NotNull(message = "The stock cannot be null")
    private Integer stock;
}
