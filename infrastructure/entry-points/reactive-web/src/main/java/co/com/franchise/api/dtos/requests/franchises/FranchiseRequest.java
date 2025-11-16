package co.com.franchise.api.dtos.requests.franchises;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class FranchiseRequest {
    @NotBlank(message = "The name cannot be blank or null")
    private String name;
}
