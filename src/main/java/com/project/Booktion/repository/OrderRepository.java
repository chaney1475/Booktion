package com.project.Booktion.repository;

import com.project.Booktion.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Order save(Order order); //주문 생성

    //void createOrder(Order order);

    // Book과 조인하여 Order를 조회하는 메서드
    @Query("SELECT o FROM Order o JOIN FETCH o.book WHERE o.id = :orderId")
    Order findOrderWithBookById(Long orderId);
}
