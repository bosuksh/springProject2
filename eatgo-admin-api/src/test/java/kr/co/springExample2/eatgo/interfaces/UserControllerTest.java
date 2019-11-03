package kr.co.springExample2.eatgo.interfaces;


import kr.co.springExample2.eatgo.application.UserService;
import kr.co.springExample2.eatgo.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private WebApplicationContext ctx;

    @MockBean
    private UserService userService;
    @Before
    public void setUp() {
        this.mvc = MockMvcBuilders.webAppContextSetup(ctx).addFilter(new CharacterEncodingFilter("UTF-8",true)).build();
    }
    @Test
    public void list() throws Exception {
        List<User> users = new ArrayList<>();
        users.add(
                User.builder()
                .name("테스터")
                .level(1L)
                .email("test@example.com")
                .build()
        );

        given(userService.getUsers()).willReturn(users);
        mvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("테스터")));

    }

    @Test
    public void create() throws Exception {
        String email= "admin@example.com";
        String name = "Administrator";
        User user = User.builder().name(name).email(email).build();

        given(userService.addUser(user)).willReturn(user);
        mvc.perform(post("/users")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"email\":\"admin@example.com\",\"name\":\"Administrator\"}"))
               // .andExpect(header().string("location","/users/1"))
                .andExpect(status().isCreated());

        verify(userService).addUser(user);
    }

}