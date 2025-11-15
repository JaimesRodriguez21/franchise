package co.com.franchise.usecase.franchise.usecases;

import co.com.franchise.model.franchise.Franchise;
import co.com.franchise.model.franchise.gateways.FranchiseRepository;
import co.com.franchise.usecase.franchise.exception.BusinessException;
import co.com.franchise.usecase.franchise.services.FranchiseService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class FranchiseUseCase implements FranchiseService {

    private final FranchiseRepository franchiseRepository;

    @Override
    public Mono<Franchise> createFranchise(Franchise franchise) {
        return this.validateUniqueFranchise(franchise.getName()).then(
                this.franchiseRepository.createFranchise(franchise))
                ;
    }

    private Mono<Void> validateUniqueFranchise(String name) {
        return franchiseRepository.findFranchiseByName(name)
                .flatMap(franchise -> Mono.error(new BusinessException("3232", "mensaje")))
                .then();
    }
}
