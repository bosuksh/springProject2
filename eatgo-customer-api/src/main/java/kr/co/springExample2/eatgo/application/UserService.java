package kr.co.springExample2.eatgo.application;

import kr.co.springExample2.eatgo.domain.User;
import kr.co.springExample2.eatgo.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {

    private UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
       this.userRepository = userRepository;
    }

    public User registerUser(String email, String name, String password) {
        User user = User.builder()
                .email(email)
                .name(name)
                .password(password)
                .build();

        return userRepository.save(user);
    }
}
