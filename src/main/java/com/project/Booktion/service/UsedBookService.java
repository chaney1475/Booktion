package com.project.Booktion.service;

import com.project.Booktion.controller.usedBook.UsedBookRegist;
import com.project.Booktion.model.UsedBook;

import java.util.List;

import com.project.Booktion.model.UsedBookOrder;
import org.springframework.stereotype.Service;

@Service
public class UsedBookService {
    public UsedBook getUsedBook(String bookId) {
        return null;
    }

    public void submitOrderForm(String userId, com.project.Booktion.controller.usedBook.UsedBookOrder usedBookOrder) {
    }

    public UsedBook submitRegistForm(UsedBookRegist usedBookRegist) {
        return null;
    }

    public List<UsedBook> getSellingUsedBooks(String memberId) {
        return null;
    }

    public List<UsedBookOrder> getSoldUsedBooks(String memberId) {
        return null;
    }

    public UsedBook findById(String bookId) { return null;
    }

    public List<UsedBook> getUsedBookList() {
        return null;
    }
}
