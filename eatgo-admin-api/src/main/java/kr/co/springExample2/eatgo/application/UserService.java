package kr.co.springExample2.eatgo.application;

import kr.co.springExample2.eatgo.domain.User;
import kr.co.springExample2.eatgo.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long id, String email, String name, Long level) {
        //TODO: restaurantService 예외처리
        User user = userRepository.findById(id).orElse(null);
        user.setName(name);
        user.setEmail(email);
        user.setLevel(level);
        userRepository.save(user);
        return user;
    }

    public User deactiveUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        user.deactivate();
        return user;
    }
}
