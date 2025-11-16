package co.com.franchise.api.dtos.requests.utils;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class NameRequest {
    @NotBlank(message = "The name cannot be blank or null")
    private String name;
}
