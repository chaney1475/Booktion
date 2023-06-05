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
        System.out.println("      " + orderForm.getBooks().size());
        for (BookForm bookForm : bookForms) {
            Book book = bookForm.getBook();
            System.out.println("넘어온 책 : " + book.getTitle());
            int quantity = bookForm.getQuantity();

            OrderItem orderItem = new OrderItem();
            orderItem.setBook(book);
            System.out.println("주문 : " + orderItem.getBook().getTitle());
            orderItem.setQuantity(quantity);
            System.out.println("주문 : " + orderItem.getQuantity());
            price += (book.getPrice() * quantity);
            System.out.println("주문금액 : " + price);

            orderItems.add(orderItem);
        }

        Order order = orderForm.getOrder();

        order.setUser(user);
        order.setOrderItems(orderItems);
        order.setOrderDate(new Date());
        order.setStatus(1); //주문 접수 설정
        order.setOrderType(1);
        order.setPrice(price);

        // Order를 데이터베이스에 저장
        orderRepository.save(order);

        // OrderItem을 데이터베이스에 저장
        for (OrderItem orderItem : orderItems) {
            orderItem.setOrder(order);
            orderItemRepository.save(orderItem);
        }

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
}
