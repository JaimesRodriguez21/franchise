package co.com.franchise.api.mapper.stores;

import co.com.franchise.api.dtos.requests.stores.StoreRequest;
import co.com.franchise.api.dtos.responses.stores.StoreResponse;
import co.com.franchise.model.store.Store;
import org.springframework.stereotype.Component;

@Component
public class StoreMapper {


    public Store toDomain(StoreRequest request) {
        return Store.builder()
                .name(request.getName())
                .build();
    }

    public StoreResponse toResponse(Store store) {
        return StoreResponse.builder()
                .id(store.getId())
                .name(store.getName())
                .franchiseId(store.getFranchiseId())
                .build();
    }

}
