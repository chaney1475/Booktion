package com.project.Booktion.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "cart")
public class Cart implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart_seq_generator")
    @SequenceGenerator(name = "cart_seq_generator", sequenceName = "CART_SEQ")
    private long cartId;
    private String clientId;
    @OneToMany
    private List<CartItem> cartItemList;

    // Constructors, getters and setters, etc.

    public Cart() {
    }

    public Cart(String clientId) {
        this.clientId = clientId;
    }

    // Getters and setters

    public long getCartId() {
        return cartId;
    }

    public void setCartId(long cartId) {
        this.cartId = cartId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

}
