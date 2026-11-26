package fr.simplon.order.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.simplon.order.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
}
