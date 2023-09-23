package ca.gbc.productservice.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

@AllArgsConstructor //instantiate object
@NoArgsConstructor //instantiate object
@Data // accessors or mutators
@Builder //instantiate object
public class ProductResponse {

    @Id
    public String Id;
    private String name;
    private String description;
    private BigDecimal price;
}
