package co.com.franchise.mongo.stores;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(value = "stores")
public class StoreDocument {
    @Id
    private String id;
    private String name;
    private String franchiseId;
}
