package co.com.franchise.api.dtos.requests.stores;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class StoreRequest {
    @NotBlank(message = "The name cannot be blank or null")
    private String name;
}
