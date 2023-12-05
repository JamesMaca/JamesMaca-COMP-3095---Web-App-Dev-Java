package ca.gbc.orderservice.service;


import ca.gbc.orderservice.dto.InventoryRequest;
import ca.gbc.orderservice.dto.InventoryResponse;
import ca.gbc.orderservice.dto.OrderLineItemDto;
import ca.gbc.orderservice.dto.OrderRequest;
import ca.gbc.orderservice.model.Order;

import ca.gbc.orderservice.model.OrderLineItem;
import ca.gbc.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl{

    private final OrderRepository orderRepository;
    private WebClient.Builder webClientBuilder;

    @Value("${inventory.service.url}")
    private String inventoryApiUri;


    public String placeOrder(OrderRequest orderRequest) {

        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItem> orderLineItems = orderRequest
                .getOrderLineItemListDto()
                .stream()
                .map(this::mapToDto)
                .toList();

        order.setOrderLineItemList(orderLineItems);




        List<InventoryRequest> inventoryRequests = order.getOrderLineItemList()
                .stream().map(orderLineItem -> InventoryRequest
                        .builder()
                        .skuCode(orderLineItem.getSkuCode())
                        .quantity(orderLineItem.getQuantity())
                        .build())
                .toList();


        List<InventoryResponse> inventoryResponseList = webClientBuilder
                .build()
                .post()
                .uri(inventoryApiUri)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(inventoryRequests).retrieve()
                .bodyToFlux(InventoryResponse.class)
                .collectList()
                .block(); //block to make this synchronous


        assert inventoryResponseList != null;
        boolean allProductsInStock = inventoryResponseList
                .stream()
                .allMatch(InventoryResponse::isSufficientStock);

        if(Boolean.TRUE.equals(allProductsInStock)){
            orderRepository.save(order);
            return "Order Placed";
        }else{
            throw new RuntimeException("Not all products are in stock, cannot place order");
        }


    }

    private OrderLineItem mapToDto(OrderLineItemDto orderLineItemDto) {
        OrderLineItem orderLineItem = new OrderLineItem();
        orderLineItem.setPrice(orderLineItemDto.getPrice());
        orderLineItem.setQuantity(orderLineItemDto.getQuantity());
        orderLineItem.setSkuCode(orderLineItemDto.getSkuCode());
        return orderLineItem;
    }

}
