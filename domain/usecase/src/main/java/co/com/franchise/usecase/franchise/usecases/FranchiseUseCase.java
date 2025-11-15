package co.com.franchise.usecase.franchise.usecases;

import co.com.franchise.model.franchise.Franchise;
import co.com.franchise.model.franchise.gateways.FranchiseRepository;
import co.com.franchise.model.store.Store;
import co.com.franchise.usecase.enums.ExceptionCodeEnum;
import co.com.franchise.usecase.exception.BusinessException;
import co.com.franchise.usecase.franchise.services.FranchiseService;
import co.com.franchise.usecase.store.services.StoreService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class FranchiseUseCase implements FranchiseService {


    private final FranchiseRepository franchiseRepository;
    private final StoreService storeService;

    @Override
    public Mono<Franchise> createFranchise(Franchise franchise) {
        return this.validateUniqueFranchise(franchise.getName()).then(
                this.franchiseRepository.createFranchise(franchise));
    }

    @Override
    public Mono<Franchise> findById(String id) {
        return franchiseRepository.findById(id);
    }

    @Override
    public Mono<Store> addStoreToFranchise(String franchiseId, Store store) {
        return franchiseRepository.findById(franchiseId)
                .switchIfEmpty(Mono.error(new BusinessException(ExceptionCodeEnum.C01FRCH02)))
                .flatMap(franchise -> {
                    store.setFranchiseId(franchiseId);
                    return storeService.createStore(store);
                });
    }

    private Mono<Void> validateUniqueFranchise(String name) {
        return franchiseRepository.findFranchiseByName(name)
                .flatMap(franchise -> Mono.error(new BusinessException(ExceptionCodeEnum.C01FRCH01)))
                .then();
    }
}
