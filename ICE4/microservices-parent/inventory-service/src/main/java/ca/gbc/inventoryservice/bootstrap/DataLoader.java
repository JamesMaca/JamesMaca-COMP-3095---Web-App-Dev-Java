package ca.gbc.inventoryservice.bootstrap;

import ca.gbc.inventoryservice.model.Inventory;
import ca.gbc.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor

public class DataLoader implements CommandLineRunner {

    private final InventoryRepository inventoryRepository;

    @Override
    public void run(String... args) throws Exception {
        if(inventoryRepository.findBySkuCode("sku_12345").isEmpty()){

            Inventory widget = Inventory.builder()
                    .skuCode("sku_12345")
                    .quantity(10)
                    .build();

            inventoryRepository.save(widget);
        }

        if(inventoryRepository.findBySkuCode("sku_789012").isEmpty()){

            Inventory widget = Inventory.builder()
                    .skuCode("sku_789012")
                    .quantity(10)
                    .build();

            inventoryRepository.save(widget);

        }

        if(inventoryRepository.findBySkuCode("sku_098765").isEmpty()){

            Inventory widget = Inventory.builder()
                    .skuCode("sku_098765")
                    .quantity(10)
                    .build();

            inventoryRepository.save(widget);

        }




    }


}
