package com.project.Booktion.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "bids")
public class Bid implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bid_seq_generator")
    @SequenceGenerator(name = "bid_seq_generator", sequenceName = "BID_SEQ")
    @Column
    private long bidId;

    @ManyToOne
    @JoinColumn(name = "auctionBookId")
    private AuctionBook auctionBook;

    @Column
    @JoinColumn(name = "clientId")
    private String bidderId;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    private int price;

    // Constructors, getters and setters, etc.

    public Bid() {
    }

    public Bid(AuctionBook auctionBook, String bidderId) {
        this.auctionBook = auctionBook;
        this.bidderId = bidderId;
    }

    // Getters and setters

    public long getBidId() {
        return bidId;
    }

    public void setBidId(long bidId) {
        this.bidId = bidId;
    }

    public AuctionBook getAuctionBook() {
        return auctionBook;
    }

    public void setAuctionBook(AuctionBook auctionBook) {
        this.auctionBook = auctionBook;
    }

    public String getBidderId() {
        return bidderId;
    }

    public void setBidderId(String bidderId) {
        this.bidderId = bidderId;
    }
}
