package co.com.franchise.mongo.products;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(value = "products")
public class ProductDocument {
    @Id
    private String id;
    private String name;
    private String storeId;
    private int stock;
}
