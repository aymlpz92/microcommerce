package fr.simplon.order.client;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import fr.simplon.order.entity.Order;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class InventoryClient {

    private final RestClient restClient;

    public InventoryClient(RestClient.Builder restClient, @Value("${inventory.base-url}") String baseUrl) {
        this.restClient = restClient.baseUrl(baseUrl).build();
    }

    public String updateInventory(UUID productId, int quantity) {
        return restClient.put()
                .uri(uriBuilder -> uriBuilder
                        .path("/inventory/update/" + productId)
                        .queryParam("quantity", quantity)
                        .build())
                .retrieve()
                .body(String.class);
    }

    public Order getInventoryById(UUID productId) {
        return restClient.get()
                .uri("/inventory/{productId}", productId)
                .retrieve()
                .body(Order.class);
    }

}
