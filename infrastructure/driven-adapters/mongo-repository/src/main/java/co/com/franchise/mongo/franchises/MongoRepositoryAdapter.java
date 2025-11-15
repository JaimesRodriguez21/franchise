package co.com.franchise.mongo.franchises;

import co.com.franchise.model.franchise.Franchise;
import co.com.franchise.model.franchise.gateways.FranchiseRepository;
import co.com.franchise.mongo.helper.AdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class MongoRepositoryAdapter extends AdapterOperations<Franchise, FranchiseDocument, String, FranchiseDBRepository>
 implements FranchiseRepository
{

    public MongoRepositoryAdapter(FranchiseDBRepository repository, ObjectMapper mapper) {
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(repository, mapper, d -> mapper.map(d, Franchise.class));
    }

    @Override
    public Mono<Franchise> createFranchise(Franchise franchise) {
        return repository
                .save(mapper.map(franchise, FranchiseDocument.class))
                .map(doc -> mapper.map(doc, Franchise.class));
    }

    @Override
    public Mono<Franchise> findFranchiseByName(String name) {
        return repository.findByNameIgnoreCase(name)
                .map(franchise -> mapper.map(franchise, Franchise.class));
    }


}
