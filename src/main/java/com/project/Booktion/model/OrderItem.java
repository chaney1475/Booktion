package com.project.Booktion.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="order_item")
public class OrderItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_item_seq")
    @SequenceGenerator(name = "order_item_seq", sequenceName = "ORDER_ITEM_SEQ", allocationSize = 1)
    @Column(name="order_item_id")
    private Long orderItemId;

    @ManyToOne
    @JoinColumn(name="order_id")
    private Order order;

    @Column(name="quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name="book_id")
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
}
