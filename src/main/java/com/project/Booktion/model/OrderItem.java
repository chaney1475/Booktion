package com.project.Booktion.model;

import org.springframework.data.annotation.Id;

import javax.persistence.*;

@Entity
@Table(name="order_item")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_item_seq")
    @SequenceGenerator(name = "order_item_seq", sequenceName = "ORDER_ITEM_SEQ", allocationSize = 1)
    @Column(name = "orderItemId")
    private Long orderItemId;

    @ManyToOne
    @JoinColumn(name = "orderId")
    private Order order;

    @Column(name = "quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "bookId")
    private Book book;

    public Long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public OrderItem() {
    }

    public OrderItem(Long orderItemId, Order order, int quantity, Book book) {
        this.orderItemId = orderItemId;
        this.order = order;
        this.quantity = quantity;
        this.book = book;
    }
}
