package kr.co.springExample2.eatgo.interfaces;

import kr.co.springExample2.eatgo.application.UserService;
import kr.co.springExample2.eatgo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public HttpEntity<?> create(@RequestBody User req) throws URISyntaxException {
        String email = req.getEmail();
        String name = req.getName();
        String password = req.getPassword();

//        User user = User.builder()
//                .id(1L)
//                .email(email)
//                .name(name)
//                .password(password)
//                .build();

        User user = userService.registerUser(email,name,password);
        String url = "/users/"+user.getId();
        return ResponseEntity.created(new URI(url)).body("{}");
    }
}

