package fr.simplon.inventory.client;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import fr.simplon.inventory.entity.dtos.ProductDTO;

@Component
public class ProductClient {

    private final RestClient restClient;

    public ProductClient(RestClient.Builder builder, @Value("${product.base-url}") String baseUrl) {
        this.restClient = builder.baseUrl(baseUrl).build();
    }

    public ProductDTO getProductInfos(UUID productId) {
        return restClient.get()
                .uri("/products/{productId}", productId)
                .retrieve()
                .body(ProductDTO.class);
    }
}
