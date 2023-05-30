package com.project.Booktion.controller.usedBook;

import com.project.Booktion.model.Address;

import java.util.Date;

public class UsedBookOrder {
    //중고책 OrderForm command class
    private long usedBookId;
    private long usedBookOrderId;
    private long buyerId;
    private Date orderDate;
    private String payment; //결제 방식
    private String company; //택배 배송사
    private String shipMessage; //배송 메세지
    private Address address;

    private String card; //카드 선택시 카드명
    private String orderName; //배송받는 사람 이름
    public long getUsedBookId() {
        return usedBookId;
    }

    public void setUsedBookId(long usedBookId) {
        this.usedBookId = usedBookId;
    }

    public long getUsedBookOrderId() {
        return usedBookOrderId;
    }

    public void setUsedBookOrderId(long usedBookOrderId) {
        this.usedBookOrderId = usedBookOrderId;
    }

    public long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(long buyerId) {
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
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
                ", address=" + address +
                '}';
    }
}
