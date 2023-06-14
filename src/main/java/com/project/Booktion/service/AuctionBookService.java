package com.project.Booktion.service;

import com.project.Booktion.model.*;
import com.project.Booktion.controller.auctionBook.AuctionOrderForm;
import com.project.Booktion.repository.AuctionBookOrderRepository;
import com.project.Booktion.repository.AuctionBookRepository;
import com.project.Booktion.repository.TempOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AuctionBookService {

    private final UserService userService;
    private final AuctionBookRepository auctionBookR;
    private final AuctionBookOrderRepository auctionBookOrderR;
    private final TempOrderRepository tempOrderRepository;
    public void submitOrder() {
    }


    public AuctionBook findById(Long bookId) {
        return auctionBookR.findById(bookId).orElse(null);
    }
    @Transactional
    public AuctionBookOrder newOrder(TempOrder tempOrder, AuctionOrderForm form) {

        Order order = new Order();
        AuctionBook auctionBook = findById(tempOrder.getAuctionBookId());
        auctionBook.setStatus(1);
        User user = userService.getUser(tempOrder.getUserId());
        int price = tempOrder.getBid().getPrice();

        order.setUser(user);
        order.setName(user.getName());
        order.setOrderDate(new Date());
        order.setOrderType(3);
        order.setAddress(form.getAddress());
        order.setShipMessage(form.getShippingMessage());
        order.setPayment(form.getPayment());
        order.setPrice(price);
        order.setCard(form.getCard());
        order.setPhoneNumber(form.getPhoneNumber());
        order.setStatus(1);
        order.setOrderItems(null);


        // Order 엔티티 생성
        AuctionBookOrder ABOrder = new AuctionBookOrder();

        // AuctionBookOrder 엔티티 생성
        ABOrder.setOrder(order);
        ABOrder.setAuctionBook(auctionBook);
        ABOrder.setBid(tempOrder.getBid());

        AuctionBookOrder savedOrder = auctionBookOrderR.save(ABOrder);
        tempOrderRepository.delete(tempOrder);
        return savedOrder;
    }

    public AuctionBook addNewBook(AuctionBook book) {
        AuctionBook saved = auctionBookR.save(book);
        return saved;
    }
}
