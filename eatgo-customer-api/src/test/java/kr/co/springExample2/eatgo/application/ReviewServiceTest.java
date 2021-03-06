package kr.co.springExample2.eatgo.application;

import kr.co.springExample2.eatgo.domain.Review;
import kr.co.springExample2.eatgo.domain.ReviewRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
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
    public void addReview() {
        Review review = Review.builder()
                .name("JOKER")
                .score(3)
                .description("delicious")
                .build();
        reviewService.addReview(1004L,"JOKER",3,"delicious");

        verify(reviewRepository).save(any());
    }

}