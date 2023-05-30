package com.project.Booktion.controller.book;

import com.project.Booktion.model.Address;
import com.project.Booktion.model.Book;
import com.project.Booktion.model.Order;

import java.util.List;

public class OrderForm {
    private List<BookForm> books;
    private List<Long> selectedCartItemIds;
    private Order order;

    public OrderForm(List<BookForm> books) {
        this.books = books;
        this.order = new Order();
    }

    public OrderForm() {
        this.order = new Order();
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
}

