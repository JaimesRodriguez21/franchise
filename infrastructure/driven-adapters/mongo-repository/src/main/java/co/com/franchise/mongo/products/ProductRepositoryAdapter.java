package co.com.franchise.mongo.products;

import co.com.franchise.model.product.Product;
import co.com.franchise.model.product.gateways.ProductRepository;
import co.com.franchise.mongo.helper.AdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class ProductRepositoryAdapter extends AdapterOperations<Product, ProductDocument, String, ProductDBRepository>
        implements ProductRepository {

    public ProductRepositoryAdapter(ProductDBRepository repository, ObjectMapper mapper) {
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(repository, mapper, d -> mapper.map(d, Product.class));
    }


    @Override
    public Mono<Product> createProduct(Product product) {
        return repository.save(mapper.map(product, ProductDocument.class))
                .map(doc -> mapper.map(doc, Product.class));
    }

    @Override
    public Mono<Product> findProductByName(String name) {
        return repository.findByNameIgnoreCase(name)
                .map(doc -> mapper.map(doc, Product.class));
    }
}