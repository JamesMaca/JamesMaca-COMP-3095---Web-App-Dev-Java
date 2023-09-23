package ca.gbc.productservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@AllArgsConstructor //instantiate object
@NoArgsConstructor //instantiate object
@Builder //instantiate object
@Data // accessors or mutators
@Document(value = "product")
public class Product {

    @Id
    public String Id;
    public String name;
    public String description;
    public BigDecimal price;
}