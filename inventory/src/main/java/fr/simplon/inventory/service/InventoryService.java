package fr.simplon.inventory.service;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

import javax.naming.InsufficientResourcesException;

import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import fr.simplon.inventory.client.ProductClient;
import fr.simplon.inventory.entity.Inventory;
import fr.simplon.inventory.entity.dtos.ProductDTO;
import fr.simplon.inventory.repository.InventoryRepository;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final ProductClient productClient;

    public InventoryService(InventoryRepository inventoryRepository, ProductClient productClient) {
        this.inventoryRepository = inventoryRepository;
        this.productClient = productClient;
    }

    public Inventory createInventory(UUID productId, int initialQuantity) {
        Inventory inventory = Inventory.builder()
                .id(null)
                .productId(productId)
                .quantity(initialQuantity)
                .build();
        return inventoryRepository.save(inventory);
    }

    public Optional<ProductDTO> getInventoryProductById(UUID productId) {
        ProductDTO productDTO = productClient.getProductInfos(productId);
        Inventory inventory = inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new IllegalArgumentException("Inventory not found for Product ID: " + productId));

        productDTO.setQuantity(inventory.getQuantity());
        return Optional.of(productDTO);
    }

    public void addStock(UUID productId, int newQuantity) {
        Inventory inventory = inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new IllegalArgumentException("Inventory not found for Product ID: " + productId));

//        int currentQuantity = inventory.getQuantity();
//        if (currentQuantity < newQuantity) {
//            throw new IllegalArgumentException("Stock insuffisant");
//        }

        inventory.setQuantity(newQuantity);
        inventoryRepository.save(inventory);

    }

    public void updateStock(UUID productId, int quantity) throws Exception {
        Inventory inventory = inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new IllegalArgumentException("Inventory not found for Product ID: " + productId));

        int currentQuantity = inventory.getQuantity();
        if (currentQuantity < quantity) {
            throw new BadRequestException("Stock insuffisant");
        }

        inventory.setQuantity(currentQuantity - quantity);
        inventoryRepository.save(inventory);

    }
}
