package kr.co.springExample2.eatgo.application;

import kr.co.springExample2.eatgo.domain.Review;
import kr.co.springExample2.eatgo.domain.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public Review addReview(Long restaurantId, String name, Integer score, String description) {
        Review review = Review.builder()
                .name(name)
                .score(score)
                .description(description)
                .build();
        review.setRestaurantId(restaurantId);
        return reviewRepository.save(review);
    }
}
