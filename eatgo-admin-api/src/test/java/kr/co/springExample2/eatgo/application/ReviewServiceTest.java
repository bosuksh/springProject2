package kr.co.springExample2.eatgo.application;

import kr.co.springExample2.eatgo.domain.Review;
import kr.co.springExample2.eatgo.domain.ReviewRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class ReviewServiceTest {

    private ReviewService reviewService;

    @Mock
    private ReviewRepository reviewRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        reviewService = new ReviewService(reviewRepository);
    }


    @Test
    public void getReviews() {
        List<Review> mockReviews = new ArrayList<>();
        mockReviews.add(Review.builder().description("Cool!").build());
        given(reviewRepository.findAll()).willReturn(mockReviews);

        List<Review> reviews = reviewService.getReviews();
        Review review = reviews.get(0);

        assertThat(review.getDescription(),is("Cool!"));
    }

}