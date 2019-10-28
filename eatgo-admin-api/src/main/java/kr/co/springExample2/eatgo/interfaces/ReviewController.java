package kr.co.springExample2.eatgo.interfaces;

import kr.co.springExample2.eatgo.application.ReviewService;
import kr.co.springExample2.eatgo.domain.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

//    @PostMapping("/restaurants/{id}/reviews")
//    public ResponseEntity<?> create(
//            @PathVariable Long id,
//            @Valid @RequestBody Review resource
//    ) throws URISyntaxException {
//       // Review review = reviewService.addReview(id,resource);
//        String url = "/restaurants/"+id+"/reviews/"+review.getId();
//
//        return ResponseEntity.created((new URI(url)))
//                .body("{}");
//    }

    @GetMapping("reviews")
    public List<Review> list() {
        List<Review> reviews = reviewService.getReviews();
       return reviews;
    }
}
