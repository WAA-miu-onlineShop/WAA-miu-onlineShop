package com.miu.waa.groupbravo.onlineshop.service;

import com.miu.waa.groupbravo.onlineshop.domain.Review;
import com.miu.waa.groupbravo.onlineshop.domain.User;

import java.util.List;

public interface ReviewService {
    public Review saveReview(Review review);
    public Review approveReview(Review review);
    public Review cancelReview(Review review);
    public Review findReviewById(Long id);
    public List<Review> findAllReviews();
    public List<Review>  findReviewsBySeller(User seller);
    public List<Review> findReviewsByBuyer(User buyer);
}
