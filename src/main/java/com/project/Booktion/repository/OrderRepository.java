package com.project.Booktion.repository;

import com.project.Booktion.model.Order;
import com.project.Booktion.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    //Order save(Order order); //주문 생성

    Order findByOrderId(long orderId);

    void deleteByOrderId(long orderId);

    List<Order> findByUser(User user);

    List<Order> findByOrderTypeAndUserUserId(int orderType, String userId);

    // 주문 ID를 기반으로 주문에 속한 OrderItem들을 조회
//    List<OrderItem> findOrderItemsByOrderId(Long orderId);


    // Book과 조인하여 Order를 조회하는 메서드
//    @Query(value="SELECT o FROM Order o JOIN FETCH o.book WHERE o.id = :orderId", nativeQuery = true)
//    Order findOrderWithBookById(Long orderId);

    //usedBook sold list가져오는 메서드
    //orderType이 2이고 book이 2이고 status가 1인상태의 order리스트를 반환함

}
