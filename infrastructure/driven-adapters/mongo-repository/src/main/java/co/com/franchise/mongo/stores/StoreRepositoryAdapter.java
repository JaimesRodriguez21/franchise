package co.com.franchise.mongo.stores;

import co.com.franchise.model.store.Store;
import co.com.franchise.model.store.gateways.StoreRepository;
import co.com.franchise.mongo.helper.AdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class StoreRepositoryAdapter extends AdapterOperations<Store, StoreDocument, String, StoreDBRepository>
        implements StoreRepository {

    public StoreRepositoryAdapter(StoreDBRepository repository, ObjectMapper mapper) {
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(repository, mapper, d -> mapper.map(d, Store.class));
    }


    @Override
    public Mono<Store> createStore(Store store) {
        return repository
                .save(mapper.map(store, StoreDocument.class))
                .map(doc -> mapper.map(doc, Store.class));
    }

    @Override
    public Mono<Store> findStoreByName(String name) {
        return repository.findByNameIgnoreCase(name)
                .map(doc -> mapper.map(doc, Store.class));
    }

    @Override
    public Flux<Store> findAllByFranchiseId(String id) {
        return repository.findAllByFranchiseId(id)
                .map(doc -> mapper.map(doc, Store.class));
    }

}
