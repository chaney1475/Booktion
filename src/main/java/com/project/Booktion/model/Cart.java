package com.project.Booktion.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cart")
public class Cart implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart_seq_generator")
    @SequenceGenerator(name = "cart_seq_generator", sequenceName = "CART_SEQ", allocationSize = 1)
    @Column(name = "cart_id")
    private long cartId;

    @Column(name = "client_id")
    private String userId;

    @OneToMany(mappedBy = "cart")
    private List<CartItem> cartItemList = new ArrayList<>();

    // Constructors, getters and setters, etc.

    public Cart() {
    }

    public Cart(String clientId) {
        this.userId = clientId;
    }

    // Getters and setters

    public long getCartId() {
        return cartId;
    }

    public void setCartId(long cartId) {
        this.cartId = cartId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<CartItem> getCartItemList() {
        return cartItemList;
    }

    public void setCartItemList(List<CartItem> cartItemList) {
        this.cartItemList = cartItemList;
    }
}