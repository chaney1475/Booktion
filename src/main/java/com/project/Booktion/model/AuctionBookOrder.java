package com.project.Booktion.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="auction_book_order")
public class AuctionBookOrder implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "auction_book_order_seq_generator")
    @SequenceGenerator(name = "auction_book_order_seq_generator", sequenceName = "AUCTION_BOOK_ORDER_SEQ", allocationSize = 1)
    private int auctionOrderId;
    @OneToOne
    @JoinColumn(name="order_id")
    private Order order;
    @OneToOne
    @JoinColumn(name="auction_book_id")
    private AuctionBook auctionBook;
    @OneToOne
    @JoinColumn(name="bid_id")
    private Bid bid;

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

}
