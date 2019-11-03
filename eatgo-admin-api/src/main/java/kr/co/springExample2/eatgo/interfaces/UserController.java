package kr.co.springExample2.eatgo.interfaces;

import kr.co.springExample2.eatgo.application.UserService;
import kr.co.springExample2.eatgo.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@Slf4j
public class UserController {

    // 1. User List
    // 2. User create-> 회원 가입
    // 3. User update
    // 4. User delete -> level: 0 => 아무것도 못함
    // (1:customer, 2. restaurant owner, 3. admin)

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> list() {
        return userService.getUsers();
    }

    @PostMapping("/users")
    public ResponseEntity<?> create(@RequestBody User req) throws URISyntaxException {
        User user = userService.addUser(req);
        String url = "/users/"+user.getId();
        return ResponseEntity.created(new URI(url)).body("{}");
    }
}
