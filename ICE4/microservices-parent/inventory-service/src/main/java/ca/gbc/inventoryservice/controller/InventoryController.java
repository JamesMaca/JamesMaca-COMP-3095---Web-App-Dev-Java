package ca.gbc.inventoryservice.controller;

import ca.gbc.inventoryservice.dto.InventoryRequest;
import ca.gbc.inventoryservice.dto.InventoryResponse;
import ca.gbc.inventoryservice.service.InventoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryServiceImpl inventoryService;

//    @GetMapping("/{sku-code}")
//    @ResponseStatus(HttpStatus.OK)
//    public List<InventoryResponse> isInStock(@PathVariable("sku-code") List<InventoryRequest> skuCode){
//        return inventoryService.isInStock(skuCode);
//    }


    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestBody List<InventoryRequest> requests){
        return inventoryService.isInStock(requests);
    }




}
