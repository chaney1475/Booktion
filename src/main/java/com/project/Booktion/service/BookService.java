package com.project.Booktion.service;

import com.project.Booktion.model.Book;
import com.project.Booktion.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookService {
    private final BookRepository bookRepository;
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book findById(String bookId) {
        return null;
    }

    public List<Book> findAll() {
        return null;
    }

    public Book findByBookIdAndBookType(long bookId, int i) {
        return bookRepository.findByBookIdAndBookType(bookId, i);
    }
}
