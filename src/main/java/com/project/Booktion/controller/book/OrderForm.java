package com.project.Booktion.controller.book;

import com.project.Booktion.model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderForm {
    private List<BookForm> books;
    private List<Long> selectedCartItemIds;
    private Order order;

    private boolean fromCart;
    // 필드와 메서드들

    public OrderForm(List<BookForm> books) {
        this.order = new Order();
        this.books = books;
    }

    public OrderForm() {
        this.order = new Order();
        this.books = new ArrayList<>();
    }

    public List<BookForm> getBooks() {
        return books;
    }

    public void setBooks(List<BookForm> books) {
        this.books = books;
    }

    public List<Long> getSelectedCartItemIds() {
        return selectedCartItemIds;
    }

    public void setSelectedCartItemIds(List<Long> selectedCartItemIds) {
        this.selectedCartItemIds = selectedCartItemIds;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public boolean isFromCart() {
        return fromCart;
    }

    public void setFromCart(boolean fromCart) {
        this.fromCart = fromCart;
    }
}

