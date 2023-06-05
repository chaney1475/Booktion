package com.project.Booktion.service;

import com.project.Booktion.model.Book;
import com.project.Booktion.model.Review;

import com.project.Booktion.model.User;
import com.project.Booktion.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public void write(Review review) {
        reviewRepository.save(review);
    }

    public List<Review> getReviewsByBookId(long bookId) {

        return reviewRepository.findByBookBookId(bookId);
    }

    public void createReview(Review review) {
        reviewRepository.save(review);
    }


    public List<Review> getReviewByUser(User user) {
        return reviewRepository.findByUserId(user);
    }
    public void deleteReview(long reviewId) {

        //reviewRepository.deleteById(reviewId);

    }

    public Review getReviewById(long reviewId) {
        return (Review) reviewRepository.findById(reviewId).orElse(null);
    }

    public void updateReview(Review review) {
        reviewRepository.save(review);
    }


}
