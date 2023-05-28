package com.project.Booktion.controller.book;

import com.project.Booktion.model.Address;
import com.project.Booktion.model.Book;
import com.project.Booktion.model.Order;

import java.util.List;

public class OrderForm {
    //일반책 OrderForm command class
    private List<BookForm> books;
    private Order order;

    public List<BookForm> getBooks() {
        return books;
    }

    public void setBooks(List<BookForm> books) {
        this.books = books;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public OrderForm(List<BookForm> books) {
        this.books = books;
    }

    public OrderForm() {
    }

    public void calculateTotalOrderPrice() {
        int totalOrderPrice = 0;
        for (BookForm bookForm : books) {
            totalOrderPrice += bookForm.getBook().getPrice() * bookForm.getQuantity();
        }
        order.setPrice(totalOrderPrice);
    }
}
