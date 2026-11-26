package fr.simplon.product.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import fr.simplon.product.client.InventoryClient;
import fr.simplon.product.entity.Product;
import fr.simplon.product.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final InventoryClient inventoryClient;

    public ProductService(ProductRepository productRepository, InventoryClient inventoryClient) {
        this.productRepository = productRepository;
        this.inventoryClient = inventoryClient;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductByID(UUID id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product createProduct(Product product) {
        Product savedProduct = productRepository.save(product);
        inventoryClient.initializeStock(product.getId());

        return savedProduct;
    }
}
