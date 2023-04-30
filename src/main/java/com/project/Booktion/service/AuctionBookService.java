package com.project.Booktion.service;

import com.project.Booktion.model.Auction;
import com.project.Booktion.model.AuctionBook;
import com.project.Booktion.model.AuctionOrder;
import com.project.Booktion.model.AuctionOrderForm;

import java.util.ArrayList;
import java.util.List;

public class AuctionBookService {
    public static AuctionBook findById(String bookId) {
        return null;
    }
    public void submitOrder() {
    }

    public AuctionOrder newOrder(String id, String bookId, AuctionOrderForm form) {
        AuctionOrder auctionOrder = new AuctionOrder();
        return auctionOrder;
    }

    public String addNewBook(AuctionBook book) {
        String bookId = "";
        return bookId;
    }

    public List<AuctionBook> allBook() {
        List<AuctionBook> auctionBooks = new ArrayList<>();
        return auctionBooks;
    }

    public List<AuctionBook> findSellingAuctionById(String id) {
        List<AuctionBook> sellingBooks = new ArrayList<>();
        return sellingBooks;
    }

    public List<AuctionBook> findSoldAuctionById(String id) {
        List<AuctionBook> soldBooks = new ArrayList<>();
        return soldBooks;
    }

    public List<Auction> getAuction() {
        List<Auction> auctions = new ArrayList<>();
        return auctions;
    }
}
