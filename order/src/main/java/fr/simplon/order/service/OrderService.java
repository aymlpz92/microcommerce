package fr.simplon.order.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import fr.simplon.order.client.InventoryClient;
import fr.simplon.order.entity.Order;
import fr.simplon.order.repository.OrderRepository;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;

    public OrderService(OrderRepository orderRepository, InventoryClient inventoryClient) {
        this.orderRepository = orderRepository;
        this.inventoryClient = inventoryClient;
    }

    public Order createOrder(UUID productId, int quantity) {
        inventoryClient.updateInventory(productId, quantity);
        Order newOrder = inventoryClient.getInventoryById(productId);
        newOrder.setQuantity(quantity);
        return orderRepository.save(newOrder);
    }

    public Order getOrderById(UUID orderId) {
        return orderRepository.findById(orderId).orElseThrow(() -> new IllegalArgumentException("Order not found with ID: " + orderId));
    }
}
