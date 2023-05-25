package com.project.Booktion.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="used_book_order")
public class UsedBookOrder {
    @Id
    private int usedOrderId;
    private int orderId;
    private int usedBookId;

    public int getUsedOrderId() {
        return usedOrderId;
    }

    public void setUsedOrderId(int usedOrderId) {
        this.usedOrderId = usedOrderId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUsedBookId() {
        return usedBookId;
    }

    public void setUsedBookId(int usedBookId) {
        this.usedBookId = usedBookId;
    }
}
