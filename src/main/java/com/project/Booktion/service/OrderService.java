package com.project.Booktion.service;

import com.project.Booktion.controller.book.BookForm;
import com.project.Booktion.controller.book.OrderForm;
import com.project.Booktion.model.Book;
import com.project.Booktion.model.Order;
import com.project.Booktion.model.OrderItem;
import com.project.Booktion.model.User;
import com.project.Booktion.repository.OrderItemRepository;
import com.project.Booktion.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final CartService cartService;
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

    public Order createOrderFromForm(OrderForm orderForm, User user) {

        orderForm.calculateTotalOrderPrice();

        List<BookForm> bookForms = orderForm.getBooks();
        List<OrderItem> orderItems = new ArrayList<>();

        for (BookForm bookForm : bookForms) {
            Book book = bookForm.getBook();
            int quantity = bookForm.getQuantity();
            //int price = book.getPrice();

            OrderItem orderItem = new OrderItem();
            orderItem.setBook(book);
            orderItem.setQuantity(quantity);
           // orderItem.setPrice(price * quantity);

            orderItems.add(orderItem);
        }

        Order order = orderForm.getOrder();
        order.setUser(user);
        order.setOrderDate(new Date());
        order.setStatus(1); //주문 접수 설정
        order.setOrderItems(orderItems);
        orderRepository.save(order);
        //orderItemRepository.save(orderItems);

        // 주문 처리 후 장바구니에서 선택된 책들을 삭제
        List<Book> cartItems = cartService.getCartItems();
        if (cartItems != null && !cartItems.isEmpty()) {
            cartService.removeCartItems(orderForm.getSelectedCartItemIds());
        }

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
}
