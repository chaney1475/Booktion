package com.project.Booktion.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
public class CartItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart_item_seq_generator")
    @SequenceGenerator(name = "cart_item_seq_generator", sequenceName = "CART_ITEM_SEQ")
    private long cartItemId;
    private long cartId;
    @JoinColumn(name="bookId")
    private Book book;
    private int quantity;

    public long getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(long cartItemId) {
        this.cartItemId = cartItemId;
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
