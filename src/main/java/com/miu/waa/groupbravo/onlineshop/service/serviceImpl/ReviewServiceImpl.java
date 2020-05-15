package com.miu.waa.groupbravo.onlineshop.service.serviceImpl;

import com.miu.waa.groupbravo.onlineshop.domain.EReviewStatus;
import com.miu.waa.groupbravo.onlineshop.domain.Review;
import com.miu.waa.groupbravo.onlineshop.domain.User;
import com.miu.waa.groupbravo.onlineshop.repository.ReviewRepository;
import com.miu.waa.groupbravo.onlineshop.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Override
    public Review saveReview(Review review) {
        review.setReviewStatus(EReviewStatus.NEW);
        return reviewRepository.save(review);
    }

    @Override
    public Review approveReview(Review review) {
        review.setReviewStatus(EReviewStatus.APPROVED);
        return reviewRepository.save(review);
    }

    @Override
    public Review cancelReview(Review review) {
        review.setReviewStatus(EReviewStatus.CANCELLED);
        return reviewRepository.save(review);
    }
    @Override
    public Review findReviewById(Long id) {
        return reviewRepository.findById(id).get();
    }

    @Override
    public List<Review> findAllReviews() {
        return (List<Review>)reviewRepository.findAll();
    }

    @Override
    public List<Review> findReviewsBySeller(User seller) {
        return reviewRepository.findBySeller(seller);
    }

    @Override
    public List<Review> findReviewsByBuyer(User buyer) {
        return reviewRepository.findByBuyer(buyer);
    }
}
