package fr.simplon.inventory.controller;

import java.awt.event.InputEvent;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.simplon.inventory.entity.Inventory;
import fr.simplon.inventory.entity.dtos.ProductDTO;
import fr.simplon.inventory.service.InventoryService;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping("/{productId}")
    public Inventory createInventory(@PathVariable UUID productId, @RequestParam(defaultValue = "0") int initialQuantity) {
        return inventoryService.createInventory(productId, initialQuantity);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Optional<ProductDTO>> getStock(@PathVariable UUID productId) {
        Optional<ProductDTO> productDTO = inventoryService.getInventoryProductById(productId);
        if (productDTO.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productDTO);
    }

    @PutMapping("/add/{productId}")
    public ResponseEntity<String> addStock(@PathVariable UUID productId, @RequestParam int newQuantity) {
        inventoryService.addStock(productId, newQuantity);
        return ResponseEntity.ok("Stock updated for Product ID: " + productId + " with new quentity: " + newQuantity);
    }

    @PutMapping("/update/{productId}")
    public ResponseEntity<String> updateStock(@PathVariable UUID productId, @RequestParam int quantity) throws Exception {
        inventoryService.updateStock(productId, quantity);
        return ResponseEntity.ok("Stock updated for Product ID: " + productId + " with new quentity: " + quantity);
    }
}
