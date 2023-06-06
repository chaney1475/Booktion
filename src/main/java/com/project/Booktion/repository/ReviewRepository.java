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

    List<Review> findByUserId(User userId);
    List<Review> findByBookBookId(long bookId);
    //Review findByReviewIdAndClientId(Long reviewId, Long clientId);

    //void deleteByUserIdAndReviewId(String userId, Long reviewId);

    //long count();

    //List<com.project.Booktion.model.Review> findByUser(User currentUser);

    //void deleteById(long reviewId);

    Optional<Object> findById(long reviewId);
}
