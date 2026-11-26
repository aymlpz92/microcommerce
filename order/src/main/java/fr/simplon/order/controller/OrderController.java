package fr.simplon.order.controller;

import java.util.UUID;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import aj.org.objectweb.asm.commons.TryCatchBlockSorter;
import fr.simplon.order.entity.Order;
import fr.simplon.order.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/{productId}")
    public ResponseEntity<Order> createOrder(@PathVariable UUID productId, @RequestParam int quantity) throws BadRequestException {
        try {
            Order order =  orderService.createOrder(productId, quantity);
            return ResponseEntity.ok(order);
        } catch (HttpClientErrorException.BadRequest exception) {
            throw new BadRequestException(exception.getResponseBodyAsString());
        }
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable UUID orderId) {
        Order order = orderService.getOrderById(orderId);
        return ResponseEntity.ok(order);
    }
}
