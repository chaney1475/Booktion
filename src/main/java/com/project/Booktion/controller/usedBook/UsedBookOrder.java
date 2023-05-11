package com.project.Booktion.controller.usedBook;

import java.util.Date;

public class UsedBookOrder {
    //중고책 OrderForm command class
    private int usedBookId;
    private int usedBookOrderId;
    private int buyerId;
    private Date orderDate;
    private String payment; //결제 방식
    private String company; //택배 배송사
    private String shipMessage; //배송 메세지

    //private String address1; //이거 과제처럼 수정하는게 어떤지?
    public int getUsedBookId() {
        return usedBookId;
    }

    public void setUsedBookId(int usedBookId) {
        this.usedBookId = usedBookId;
    }

    public int getUsedBookOrderId() {
        return usedBookOrderId;
    }

    public void setUsedBookOrderId(int usedBookOrderId) {
        this.usedBookOrderId = usedBookOrderId;
    }

    public int getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getShipMessage() {
        return shipMessage;
    }

    public void setShipMessage(String shipMessage) {
        this.shipMessage = shipMessage;
    }

    @Override
    public String toString() {
        return "UsedBookOrder{" +
                "usedBookId=" + usedBookId +
                ", usedBookOrderId=" + usedBookOrderId +
                ", buyerId=" + buyerId +
                ", orderDate=" + orderDate +
                ", payment='" + payment + '\'' +
                ", company='" + company + '\'' +
                ", shipMessage='" + shipMessage + '\'' +
                '}';
    }
}
