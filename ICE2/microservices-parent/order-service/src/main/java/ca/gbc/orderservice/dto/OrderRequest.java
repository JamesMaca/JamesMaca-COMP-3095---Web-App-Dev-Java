package ca.gbc.orderservice.dto;

import ca.gbc.orderservice.model.OrderLineItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

    private List<OrderLineItem> orderLineItemListDto = new ArrayList<>();

}
