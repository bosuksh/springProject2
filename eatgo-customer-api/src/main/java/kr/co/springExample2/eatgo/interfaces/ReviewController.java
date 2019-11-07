package kr.co.springExample2.eatgo.interfaces;

import io.jsonwebtoken.Claims;
import kr.co.springExample2.eatgo.application.ReviewService;
import kr.co.springExample2.eatgo.domain.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/restaurants/{id}/reviews")
    public ResponseEntity<?> create(
            Authentication authentication,
            @PathVariable Long id,
            @Valid @RequestBody Review resource
    ) throws URISyntaxException {
        Claims claims = (Claims) authentication.getPrincipal();
        String description = resource.getDescription();
        Integer score = resource.getScore();
        String name = claims.get("name",String.class);
        Review review = reviewService.addReview(id,name,score,description);
        String url = "/restaurants/"+id+"/reviews/"+review.getId();

        return ResponseEntity.created((new URI(url)))
                .body("{}");
    }
}
