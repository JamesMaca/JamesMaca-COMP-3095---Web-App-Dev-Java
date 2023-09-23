package ca.gbc.productservice.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor //instantiate object
@NoArgsConstructor //instantiate object
@Data // accessors or mutators
@Builder //instantiate object
public class ProductRequest {

    private String name;
    private String description;
    private BigDecimal price;



}
