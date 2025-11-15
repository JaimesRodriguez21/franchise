package co.com.franchise.api.mapper;

import co.com.franchise.api.dtos.requests.franchises.FranchiseRequest;
import co.com.franchise.api.dtos.responses.franchises.FranchiseResponse;
import co.com.franchise.model.franchise.Franchise;
import org.springframework.stereotype.Component;

@Component
public class FranchiseMapper {

    public Franchise toDomain(FranchiseRequest request) {
        return Franchise.builder()
                .name(request.getName())
                .build();
    }

    public FranchiseResponse toResponse(Franchise franchise) {
        return new FranchiseResponse(
                franchise.getId(),
                franchise.getName()
        );
    }
}