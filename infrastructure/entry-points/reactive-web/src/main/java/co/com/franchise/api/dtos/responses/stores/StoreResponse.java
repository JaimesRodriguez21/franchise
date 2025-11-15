package co.com.franchise.api.dtos.responses.stores;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class StoreResponse {
    private String id;
    private String name;
    private String franchiseId;
}
