package com.project.Booktion.repository;

import com.project.Booktion.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    //void save(List<OrderItem> orderItems);

    OrderItem findByBookBookId(long bookId);
}
