package com.project.Booktion.service;

import com.project.Booktion.controller.usedBook.UsedBookOrder;
import com.project.Booktion.controller.usedBook.UsedBookRegist;
import com.project.Booktion.model.Book;
import com.project.Booktion.model.Order;
import com.project.Booktion.model.UsedBook;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.project.Booktion.model.User;
import com.project.Booktion.repository.BookRepository;
import com.project.Booktion.repository.OrderRepository;
import com.project.Booktion.repository.UsedBookRepository;
import com.project.Booktion.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class UsedBookService {
    private final UsedBookRepository usedBookRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public UsedBook getUsedBookById(long bookId) {
        Optional<UsedBook> result = usedBookRepository.findById(bookId);
        if(result.isPresent()) return result.get();
        return null;
    }

    public void submitOrderForm(User user, UsedBookOrder order) {
        // 판매자 정보 가져오기
        User seller = userRepository.findById(user.getUserId()).orElse(null);
        if (seller == null) {
            throw new IllegalArgumentException("Seller with ID " + user.getUserId() + " does not exist.");
        }

        // UsedBookOrder 정보로 Order 엔티티 생성
        Order newOrder = new Order();
        newOrder.setUser(seller);
        newOrder.setOrderDate(new Date());
        newOrder.setName(order.getOrderName());
        newOrder.setAddress(order.getAddress());
        newOrder.setShipMessage(order.getShipMessage());
        //newOrder.setPrice(); //order시의 정보를 어떻게 가져오는?
        newOrder.setPayment(order.getPayment());
        newOrder.setCard(order.getCard());
        newOrder.setOrderType(2);

        // Order 엔티티 저장
        orderRepository.save(newOrder);

        // UsedBook의 상태 변경
        UsedBook usedBook = usedBookRepository.findById(order.getUsedBookId()).orElse(null);
        if (usedBook == null) {
            throw new IllegalArgumentException("Used Book with ID " + order.getUsedBookId() + " does not exist.");
        }
        usedBook.setStatus(1);
        usedBookRepository.save(usedBook);
    }

    public UsedBook submitRegistForm(UsedBookRegist usedBookRegist) {
        //book 등록
        Book book = new Book();
        book.setBookType(2); //bookType 중고책
        book.setIsbn(usedBookRegist.getIsbn());
        book.setTitle(usedBookRegist.getTitle());
        book.setPrice(usedBookRegist.getPrice());
        //book.setSellerId(usedBookRegist.getSellerId()); //book model 수정해야함
        //book = bookRepository.saveAndFlush(Book); //DB에 저장

        //usedBook 등록
        UsedBook usedBook = new UsedBook();
        usedBook.setBook(book);
        usedBook.setStatus(0); //등록 중이기 때문에 안 팔린 상태
        usedBook.setShippingCompany(usedBookRegist.getCompany());
        //usedBook = usedBookRepository.saveAllAndFlush(usedBook);
        return null;
    }

    public List<UsedBook> getSellingUsedBooks(String memberId) {
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
}
