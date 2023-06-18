package com.project.Booktion.repository;

import com.project.Booktion.model.Order;
import com.project.Booktion.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Order findByOrderId(long orderId);

    void deleteByOrderId(long orderId);

    List<Order> findByUser(User user);

    List<Order> findByOrderTypeAndUserUserId(int orderType, String userId);

}
