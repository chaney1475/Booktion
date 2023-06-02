package com.project.Booktion.repository;

import com.project.Booktion.model.Book;
import com.project.Booktion.model.Review;
import com.project.Booktion.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByBook(Book book);

    Review findByReviewIdAndUser_UserId(Long reviewId, Long userId);
    Review save(Review review);

    void deleteByUser_UserIdAndReviewId(String userId, Long reviewId);

    long count();

    List<com.project.Booktion.model.Review> findByUser(User currentUser);

    void deleteById(long reviewId);

    Optional<Review> findById(long reviewId);
}
