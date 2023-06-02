package com.project.Booktion.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "bids")
public class Bid implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bid_seq_generator")
    @SequenceGenerator(name = "bid_seq_generator", sequenceName = "BID_SEQ", allocationSize = 1)
    @Column
    private long bidId;

    @ManyToOne
    @JoinColumn(name = "auction_book_id")
    private AuctionBook auctionBook;

    @Column
    private String bidderId;
    @Column
    private int price;


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    // Constructors, getters and setters, etc.

    public Bid() {
    }

    public Bid(AuctionBook auctionBook, String bidderId, int price) {
        this.auctionBook = auctionBook;
        this.bidderId = bidderId;
        this.price = price;
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
