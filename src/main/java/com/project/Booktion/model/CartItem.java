package com.project.Booktion.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table
public class CartItem implements Serializable {
    @Id
    private long CartItemId;
    private long cartId;
    @JoinColumn(name="bookId")
    private Book book;
    private int quantity;

    public long getCartItemId() {
        return CartItemId;
    }

    public void setCartItemId(long cartItemId) {
        CartItemId = cartItemId;
    }

    public long getCartId() {
        return cartId;
    }

    public void setCartId(long cartId) {
        this.cartId = cartId;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
