package co.com.franchise.usecase.store.usecases;

import co.com.franchise.model.store.Store;
import co.com.franchise.model.store.gateways.StoreRepository;
import co.com.franchise.usecase.enums.ExceptionCodeEnum;
import co.com.franchise.usecase.exception.BusinessException;
import co.com.franchise.usecase.store.services.StoreService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class StoreUseCase implements StoreService {
    private final StoreRepository storeRepository;

    @Override
    public Mono<Store> createStore(Store store) {
        return this.validateUniqueFranchise(store.getName()).then(
                this.storeRepository.createStore(store));
    }

    private Mono<Void> validateUniqueFranchise(String name) {
        return storeRepository.findStoreByName(name)
                .flatMap(franchise -> Mono.error(new BusinessException(ExceptionCodeEnum.C01STOR01)))
                .then();
    }
}
