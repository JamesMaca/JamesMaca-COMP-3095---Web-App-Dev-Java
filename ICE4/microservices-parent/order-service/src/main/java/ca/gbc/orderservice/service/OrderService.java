package ca.gbc.orderservice.service;

import ca.gbc.orderservice.dto.OrderRequest;
import org.springframework.web.reactive.function.client.WebClient;

public interface OrderService {

//    void placeOrder(OrderRequest orderRequest);
    String placeOrder(OrderRequest orderRequest);

}
