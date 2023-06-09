package com.project.Booktion.repository;

import com.project.Booktion.model.Review;
import com.project.Booktion.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByUserId(User userId);
    List<Review> findByBookBookId(long bookId);
    Optional<Object> findById(long reviewId);

    @Query("SELECT r.book.bookId FROM Review r GROUP BY r.book.bookId ORDER BY COUNT(r.book.bookId) DESC")
    List<Long> findTop8BookIdsOrderByReviewCountDesc();
}
