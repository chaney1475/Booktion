package com.project.Booktion.controller.book;

import com.project.Booktion.model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderForm {
    private List<BookForm> books;
    private List<Long> selectedCartItemIds;
    private Order order;
    private List<Long> selectedBooks; // 선택된 책들의 ID를 저장하는 리스트

    public OrderForm(List<BookForm> books) {
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

    public void calculateTotalOrderPrice() {
        int totalOrderPrice = 0;
        for (BookForm bookForm : books) {
            totalOrderPrice += bookForm.getBook().getPrice() * bookForm.getQuantity();
        }
        order.setPrice(totalOrderPrice);
    }

    public List<Long> getSelectedBooks() {
        return selectedBooks;
    }

    public void setSelectedBooks(List<Long> selectedBooks) {
        this.selectedBooks = selectedBooks;
    }
}

