package co.com.franchise.usecase.franchises.usecases;

import co.com.franchise.model.franchise.Franchise;
import co.com.franchise.model.franchise.gateways.FranchiseRepository;
import co.com.franchise.model.objects.ProductWithStore;
import co.com.franchise.model.store.Store;
import co.com.franchise.usecase.enums.ExceptionCodeEnum;
import co.com.franchise.usecase.exceptions.BusinessException;
import co.com.franchise.usecase.franchises.services.FranchiseService;
import co.com.franchise.usecase.stores.services.StoreService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

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
    public Mono<Store> addStoreToFranchise(String franchiseId, Store store) {
        return franchiseRepository.findById(franchiseId)
                .switchIfEmpty(Mono.error(new BusinessException(ExceptionCodeEnum.C01FRCH02)))
                .flatMap(franchise -> {
                    store.setFranchiseId(franchiseId);
                    return storeService.createStore(store);
                });
    }

    @Override
    public Flux<ProductWithStore> getMaxStockProductsByFranchise(String franchiseId) {
        return franchiseRepository.findById(franchiseId)
                .switchIfEmpty(Mono.error(new BusinessException(ExceptionCodeEnum.C01FRCH02)))
                .flatMapMany(franchise -> storeService.findMaxStockProductByFranchiseId(franchise.getId()));
    }

    @Override
    public Mono<Franchise> updateFranchiseName(String franchiseId, String newName) {
        return franchiseRepository.findById(franchiseId)
                .switchIfEmpty(Mono.error(new BusinessException(ExceptionCodeEnum.C01FRCH02)))
                .flatMap(existingFranchise ->
                        franchiseRepository.findFranchiseByName(newName)
                                .flatMap(exists -> {
                                    if (!Objects.equals(exists.getId(), existingFranchise.getId()) && !existingFranchise.getName().equals(newName)) {
                                        return Mono.error(new BusinessException(ExceptionCodeEnum.C01FRCH03));
                                    }
                                    existingFranchise.setName(newName);
                                    return franchiseRepository.updateFranchise(existingFranchise);
                                })
                );
    }

    private Mono<Void> validateUniqueFranchise(String name) {
        return franchiseRepository.findFranchiseByName(name)
                .flatMap(franchise -> Mono.error(new BusinessException(ExceptionCodeEnum.C01FRCH01)))
                .then();
    }
}
