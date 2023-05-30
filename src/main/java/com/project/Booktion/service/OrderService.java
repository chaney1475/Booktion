package com.project.Booktion.service;

import com.project.Booktion.controller.book.BookForm;
import com.project.Booktion.controller.book.OrderForm;
import com.project.Booktion.model.Book;
import com.project.Booktion.model.Order;
import com.project.Booktion.model.OrderItem;
import com.project.Booktion.model.User;
import com.project.Booktion.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

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

    public Order createOrderFromForm(OrderForm orderForm, User user) {
        Order order = orderForm.getOrder();
        order.setUser(user);
        order.setOrderDate(new Date());

        List<OrderItem> orderItems = new ArrayList<>();
        for (BookForm bookForm : orderForm.getBooks()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setBook(bookForm.getBook());
            orderItem.setQuantity(bookForm.getQuantity());
            orderItems.add(orderItem);
        }
        order.setOrderItems(orderItems);

        return orderRepository.save(order);
    }

}
