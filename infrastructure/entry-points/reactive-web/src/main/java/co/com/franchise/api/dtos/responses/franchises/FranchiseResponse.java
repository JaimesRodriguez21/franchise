package co.com.franchise.api.dtos.responses.franchises;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FranchiseResponse {
    private String id;
    private String name;
}