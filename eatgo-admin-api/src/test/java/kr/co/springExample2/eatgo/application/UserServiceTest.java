package kr.co.springExample2.eatgo.application;

import kr.co.springExample2.eatgo.domain.User;
import kr.co.springExample2.eatgo.domain.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class UserServiceTest {

    private UserService userService;
    @Mock
    private UserRepository userRepository;
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        userService = new UserService(userRepository);
    }

    @Test
    public void getUsers(){
        List<User> mockUsers = new ArrayList<>();


        mockUsers.add(
                User.builder()
                        .name("테스터")
                        .level(1L)
                        .email("test@example.com")
                        .build()
        );

        given(userRepository.findAll()).willReturn(mockUsers);
        List<User> users = userService.getUsers();
        User user = users.get(0);
        assertThat(user.getName(), is("테스터"));
    }

    @Test
    public void addUser(){

        String email="admin@example.com";
        String name = "Administrator";
        User mockUser = User.builder().name(name).email(email).build();
        given(userRepository.save(any())).willReturn(mockUser);

        User user = userService.addUser(mockUser);

        assertThat(user.getName(), is(name));
    }

    @Test
    public void updateUser() {
        Long id = 1004L;
        String email="admin@example.com";
        String name = "Superman";
        Long level = 100L;

        User mockUser = User.builder().id(id).name("Administrator").email(email).level(1L).build();
        given(userRepository.findById(id)).willReturn(Optional.of(mockUser));

        User user = userService.updateUser(id,email,name,level);

        verify(userRepository).findById(eq(id));

        assertThat(user.getName(),is("Superman"));
        assertThat(user.isAdmin(),is(true));
    }
}