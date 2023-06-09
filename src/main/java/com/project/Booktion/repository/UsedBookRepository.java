package com.project.Booktion.repository;

import com.project.Booktion.model.UsedBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsedBookRepository extends JpaRepository<UsedBook, Long> {
    List<UsedBook> findByStatusAndBookUserUserId(int status, String sellerId);
    UsedBook findByBookBookId(Long bookId);
}
