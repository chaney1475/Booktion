package com.project.Booktion.service;

import com.project.Booktion.model.*;
import com.project.Booktion.controller.auctionBook.AuctionOrderForm;
import com.project.Booktion.repository.AuctionBookOrderRepository;
import com.project.Booktion.repository.AuctionBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AuctionBookService {

    private final UserService userService;
    private final AuctionBookRepository auctionBookR;
    private final AuctionBookOrderRepository auctionBookOrderR;
    public void submitOrder() {
    }


    public AuctionBook findById(Long bookId) {
        return auctionBookR.findById(bookId).orElse(null);
    }
    public AuctionBookOrder newOrder(TempOrder tempOrder, AuctionOrderForm form) {

        Order order = new Order();
        AuctionBook auctionBook = findById(tempOrder.getAuctionBookId());
        User user = userService.getUser(tempOrder.getUserId());

        order.setName(user.getName());
        order.setOrderDate(new Date());
        order.setOrderType(3);
        order.setAddress(new Address(form.getShippingAddress1(), form.getShippingAddress2(), form.getZipcode()));
        order.setPrice(form.getPrice());
        order.setCard(form.getCard());

        // Order 엔티티 생성
        AuctionBookOrder ABOrder = new AuctionBookOrder();

        // AuctionBookOrder 엔티티 생성
        ABOrder.setOrder(order);
        ABOrder.setAuctionBook(auctionBook);
        ABOrder.setBid(tempOrder.getBid());

        AuctionBookOrder savedOrder = auctionBookOrderR.save(ABOrder);
        return savedOrder;
    }

    public AuctionBook addNewBook(AuctionBook book) {
        AuctionBook saved = auctionBookR.save(book);
        return saved;
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

    public List<Bid> getAuction() {
        List<Bid> bids = new ArrayList<>();
        return bids;
    }

    public List<AuctionBookOrder> findOrderBySeller(Long id) {
        List<AuctionBookOrder> orderList = new ArrayList<>();
        return orderList;
    }

    public void getTempOrder(String userId) {

    }

}
