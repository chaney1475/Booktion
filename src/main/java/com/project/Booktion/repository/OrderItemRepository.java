package com.project.Booktion.repository;

import com.project.Booktion.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    OrderItem findByBookBookId(long bookId);
}
