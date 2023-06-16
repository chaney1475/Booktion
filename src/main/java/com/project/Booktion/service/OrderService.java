package com.project.Booktion.service;

import com.project.Booktion.controller.book.BookForm;
import com.project.Booktion.controller.book.OrderForm;
import com.project.Booktion.model.*;
import com.project.Booktion.repository.CartRepository;
import com.project.Booktion.repository.OrderItemRepository;
import com.project.Booktion.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final CartRepository cartRepository;
    private final OrderItemRepository orderItemRepository;

//    public List<Order> findAll() {
//        return null;
//    }
//
//    public Order findById(long userId) {
//        return null;
//    }

    public Order findByOrderId(long orderId) {
        return orderRepository.findByOrderId(orderId);
    }

    @Transactional
    public Order createOrderFromForm(OrderForm orderForm, User user) {

        List<BookForm> bookForms = orderForm.getBooks();
        List<OrderItem> orderItems = new ArrayList<>();
        int price = 0;

        for (BookForm bookForm : bookForms) {
            Book book = bookForm.getBook();
            int quantity = bookForm.getQuantity();

            OrderItem orderItem = new OrderItem();
            orderItem.setBook(book);
            orderItem.setQuantity(quantity);

            price += (book.getPrice() * quantity);

            orderItems.add(orderItem);
        }

        Order order = orderForm.getOrder();
        order.setUser(user);
        order.setOrderDate(new Date());
        order.setStatus(0);
        order.setOrderType(1);
        order.setPrice(price);


        for (OrderItem orderItem : orderItems) {
            order.addOrderItem(orderItem); // Order에 OrderItem 추가
        }

        orderRepository.save(order); // order 저장 후 orderId 생성
//        for (OrderItem orderItem : orderItems) {
//            orderItem.setOrder(order); // orderId가 생성된 후에 orderItem에 order 설정
//            System.out.println("order.orderId " + order.getOrderId());
//            System.out.println("orderItem.order " + orderItem.getOrder().getOrderId() + " " + orderItem.getOrderItemId() + " " + orderItem.getBook().getBookId() + " " + orderItem.getQuantity());
//            //orderItemRepository.save(orderItem);
//        }

        // 주문 처리 후 장바구니에서 모든 아이템을 삭제
        if (orderForm.isFromCart()) {
            cartService.clearCartByUserId(user.getUserId());
        }
        // 선택된 아이템만 장바구니에서 삭제
//        if (orderForm.isFromCart()) {
//            List<BookForm> selectedBooks = orderForm.getBooks();
//            for (BookForm selectedBook : selectedBooks) {
//                cartService.removeItemFromCart(user.getUserId(), selectedBook.getBook().getBookId());
//            }
//        }

        return order;
    }

    public void updateOrder(Order order) {
        orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public void deleteOrder(long orderId) {
        orderRepository.deleteByOrderId(orderId);
    }

    public List<OrderItem> getOrderItemsByOrderId(long orderId) {
        Order order = orderRepository.findByOrderId(orderId);
        if (order == null) {
            throw new RuntimeException("해당 주문을 찾을 수 없습니다.");
        }

        List<OrderItem> orderItems = order.getOrderItems();
        if (orderItems == null) {
            throw new RuntimeException("주문 상품 목록을 찾을 수 없습니다.");
        }

        return orderItems;
    }

    public List<Order> getOrdersByUser(User user) {
        return orderRepository.findByUser(user);
    }

    public List<Order> findMyBasicBookOrder(String userId) {
        return orderRepository.findByOrderTypeAndUserUserId(1, userId);
    }
}