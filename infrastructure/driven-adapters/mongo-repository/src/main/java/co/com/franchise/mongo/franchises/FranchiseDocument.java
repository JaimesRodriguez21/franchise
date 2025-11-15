package co.com.franchise.mongo.franchises;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(value = "franchises")
public class FranchiseDocument {
    @Id
    private String id;
    private String name;
}