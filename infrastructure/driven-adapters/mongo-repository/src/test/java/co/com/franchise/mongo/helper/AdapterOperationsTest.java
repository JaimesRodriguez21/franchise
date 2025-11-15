package co.com.franchise.mongo.helper;

import co.com.franchise.mongo.franchises.FranchiseDBRepository;
import co.com.franchise.mongo.franchises.MongoRepositoryAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.reactivecommons.utils.ObjectMapper;
import reactor.core.publisher.Flux;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class AdapterOperationsTest {

    @Mock
    private FranchiseDBRepository repository;

    @Mock
    private ObjectMapper objectMapper;

    private MongoRepositoryAdapter adapter;

    private Object entity;
    private Flux<Object> entities;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        when(objectMapper.map("value", Object.class)).thenReturn("value");

        adapter = new MongoRepositoryAdapter(repository, objectMapper);

        entity = "value";
        entities = Flux.just(entity);
    }

    @Test
    void testSave() {
        /*when(repository.save(entity)).thenReturn(Mono.just("value"));

        StepVerifier.create(adapter.save(entity))
                .expectNext("value")
                .verifyComplete();
         */
    }


}
