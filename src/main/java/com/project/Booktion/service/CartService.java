package com.project.Booktion.service;

import com.project.Booktion.model.Book;

import java.util.List;

import com.project.Booktion.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class CartService {
    private final BookRepository bookRepository;

    @Autowired
    public CartService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    public List<Book> findAll() {return null;}
    public List<Book> getCartItems() {
        return bookRepository.findAll();
    }

    public boolean removeCartItem(long bookId) {
        return true;
    }
    public void updateCartSession(HttpSession session) {

    }
}
