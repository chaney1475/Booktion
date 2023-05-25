package com.project.Booktion.model;

import javax.persistence.*;

@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long cartId;

    @Column
    @JoinColumn(name = "clientId")
    private String clientId;

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
