package kr.co.springExample2.eatgo.application;

import kr.co.springExample2.eatgo.domain.Review;
import kr.co.springExample2.eatgo.domain.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sound.sampled.ReverbType;
import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public List<Review> getReviews() {
        return reviewRepository.findAll();
    }
}
