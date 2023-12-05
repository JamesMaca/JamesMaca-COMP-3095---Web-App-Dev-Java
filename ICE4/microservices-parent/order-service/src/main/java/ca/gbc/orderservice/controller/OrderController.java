package ca.gbc.orderservice.controller;


import ca.gbc.orderservice.dto.OrderRequest;
import ca.gbc.orderservice.model.Order;
import ca.gbc.orderservice.service.OrderServiceImpl;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import jdk.jfr.StackTrace;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderServiceImpl orderService;

//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    @CircuitBreaker(name = "inventory", fallbackMethod = "placeOrderFallback")
//    public String placeOrder(@RequestBody OrderRequest request){
//        log.info("Order Placed Successfuly!");
//        orderService.placeOrder(request);
//
//        return "Order Placed Successfuly!";
//    }
//
//    public String placeOrderFallback(OrderRequest request, RuntimeException e){
//        log.error("Exception is: {}", e.getMessage());
//        return "FALLBACK INVOKED: Order Placed Failed. PLease try again later.";
//    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "inventory", fallbackMethod = "placeOrderFallback")
    @TimeLimiter(name = "inventory")
    @Retry(name = "inventory")
    public CompletableFuture<String> placeOrder(@RequestBody OrderRequest request){
        log.info("Order Placed Successfuly!");
        orderService.placeOrder(request);

//        return "Order Placed Successfuly!";
        return CompletableFuture.supplyAsync(() -> orderService.placeOrder(request));
    }

    public CompletableFuture<String> placeOrderFallback(OrderRequest request, RuntimeException e){
        log.error("Exception is: {}", e.getMessage());
        return CompletableFuture.supplyAsync(() -> "FALLBACK INVOKED: Order Placed Failed. PLease try again later.");
    }


}















