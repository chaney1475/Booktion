package com.project.Booktion.service;

import com.project.Booktion.controller.usedBook.UsedBookOrder;
import com.project.Booktion.model.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.project.Booktion.repository.*;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Slf4j // 로그 찍는 기능
@Service
public class UsedBookService {
    private final UsedBookRepository usedBookRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public UsedBook getUsedBookByBookId(long bookId) {
        UsedBook usedBook = usedBookRepository.findByBookBookId(bookId);
        if(usedBook != null) return usedBook;
        return null;
    }

    public void submitOrderForm(long bookId, UsedBookOrder order) {
        // 판매자 정보 가져오기
        Optional<User> buyer = userRepository.findById(order.getBuyerId());
        //저장을 위한 book정보 생성
        Optional<Book> book = bookRepository.findById(bookId);
        //order item생성
        OrderItem orderItem = new OrderItem();
        orderItem.setBook(book.get());
        orderItem.setQuantity(1);

        // UsedBookOrder 정보로 Order 엔티티 생성
        Order newOrder = new Order();
        newOrder.setUser(buyer.get());
        newOrder.setOrderDate(new Date());
        newOrder.setName(order.getOrderName());
        newOrder.setAddress(order.getAddress());
        newOrder.setShipMessage(order.getShipMessage());
        newOrder.setPrice(book.get().getPrice());
        newOrder.setPayment(order.getPayment());
        newOrder.setCard(order.getCard());
        newOrder.setOrderType(2);
        newOrder.setStatus(0);
        newOrder.setPhoneNumber(order.getPhoneNumber());

        newOrder.addOrderItem(orderItem);

        // Order 엔티티 저장 & orderItem 저장
        orderRepository.save(newOrder);

        // UsedBook의 상태 변경
        Optional<UsedBook> targetUsedBook = usedBookRepository.findById(order.getUsedBookId());
        UsedBook temp = targetUsedBook.get();
        temp.setStatus(1);
        usedBookRepository.save(temp);
    }

    public List<UsedBook> getSellingUsedBooks(String memberId) {
        return usedBookRepository.findByStatusAndBook_User_UserId(0, memberId);
    }

    public List<UsedBook> getSoldUsedBooks(String memberId) {
        return usedBookRepository.findByStatusAndBook_User_UserId(0, memberId);
    }


    public List<Order> findOrderBySeller(String memberId) {
        //return orderRepository.findByUserUserIdAndOrderTypeAndBookBookTypeAndBookStatus(memberId, 2, 2, 1);
        return null;
    }

    public List<Book> getAllUsedBookList(int bookType) {
        return bookRepository.findByBookType(bookType);
    }

    public List<Book> getNewBookList(int bookType) {
        return null;
    }
    public List<Book> getSaleBookList(int bookType) {
        return null;
    }

    public UsedBook getUsedBookById(long usedBookId) {
        Optional<UsedBook> result = usedBookRepository.findById(usedBookId);
        if(result.isPresent()) return result.get();
        return null;
    }
}
