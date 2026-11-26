package fr.simplon.product.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import fr.simplon.product.entity.Product;
import fr.simplon.product.service.ProductService;

@Configuration
public class ProductInitializer {
//
//    @Bean
//    CommandLineRunner initializeProduct(ProductService productService) {
//        return args -> {
//            Product product1 = productService.createProduct(new Product(null, "un livre", "livre", "livre génial"));
//
//            Product product2 = productService.createProduct(new Product(null, "téléphone", "high-tech", "smartphone"));
//
//            Product product3 = productService.createProduct(new Product(null, "voiture", "véhicule", "voiture en bon état"));
//
//            System.out.println("ID: " + product1.getId() + ", Name : " + product1.getName());
//            System.out.println("ID: " + product2.getId() + ", Name : " + product2.getName());
//            System.out.println("ID: " + product3.getId() + ", Name : " + product3.getName());
//
//
//        };
//    }
}
