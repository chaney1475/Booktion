package com.project.Booktion.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="auction_book_order")
public class AuctionBookOrder implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int auctionOrderId;
    @OneToOne
    @JoinColumn(name="orderId")
    private Order order;
    @OneToOne
    @JoinColumn(name="auctionBookId")
    private AuctionBook auctionBook;
    @OneToOne
    @JoinColumn(name="bidId")
    private Bid bid;
    private int bidId;

    public int getAuctionOrderId() {
        return auctionOrderId;
    }

    public void setAuctionOrderId(int auctionOrderId) {
        this.auctionOrderId = auctionOrderId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public AuctionBook getAuctionBook() {
        return auctionBook;
    }

    public void setAuctionBook(AuctionBook auctionBook) {
        this.auctionBook = auctionBook;
    }

    public Bid getBid() {
        return bid;
    }

    public void setBid(Bid bid) {
        this.bid = bid;
    }

    public int getBidId() {
        return bidId;
    }

    public void setBidId(int bidId) {
        this.bidId = bidId;
    }
}
