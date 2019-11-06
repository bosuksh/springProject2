package kr.co.springExample2.eatgo.interfaces;

import kr.co.springExample2.eatgo.application.UserService;
import kr.co.springExample2.eatgo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class SessionController {


    @Autowired
    private UserService userService;

    @PostMapping("/session")
    public HttpEntity<SessionResponseDTO> create(@RequestBody SessionRequestDTO req) throws URISyntaxException {

        String url = "/session";

        User user = userService.authenticate(req.getEmail(),req.getPassword());
        String accessToken = user.getAccessToken();

        SessionResponseDTO sessionResponseDto = SessionResponseDTO.builder().accessToken(accessToken).build();

        return ResponseEntity.created(new URI(url))
                .body(sessionResponseDto);
    }
}

