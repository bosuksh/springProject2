package kr.co.springExample2.eatgo.interfaces;

import kr.co.springExample2.eatgo.application.EmailNotExistedException;
import kr.co.springExample2.eatgo.application.UserService;
import kr.co.springExample2.eatgo.application.WrongPasswordException;
import kr.co.springExample2.eatgo.domain.User;
import kr.co.springExample2.eatgo.utils.JwtUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.validation.constraints.NotEmpty;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(SessionController.class)
public class SessionControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private JwtUtil jwtUtil;

    @MockBean
    private UserService userService;

    @Test
    public void createWithValidAttributes() throws Exception {

        String email = "tester@example.com";
        String password = "test";
        // E-mail, Name, Password
        // 201
        String name = "tester";
        Long id = 1004L;
        User mockUser = User.builder().id(id).name(name).password("ACCESSTOKE").build();
        given(userService.authenticate(email,password)).willReturn(mockUser);
        given(jwtUtil.createToken(id,name)).willReturn("header.payload.signature");

        mvc.perform(post("/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"tester@example.com\",\"password\":\"test\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location","/session"))
                .andExpect(content().string(containsString("{\"accessToken\":\"header.payload.signature\"")));

        verify(userService).authenticate(eq(email),eq(password));
    }

    @Test
    public void createWithNotExistedEmail() throws Exception {

        // E-mail, Name, Password
        // 201
        given(userService.authenticate("x@example.com","test"))
                .willThrow(EmailNotExistedException.class);

        mvc.perform(post("/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"x@example.com\",\"password\":\"test\"}"))
                .andExpect(status().isBadRequest());


        verify(userService).authenticate(eq("x@example.com"),eq("test"));
    }

    @Test
    public void createWithWrongPassword() throws Exception {

        // E-mail, Name, Password
        // 201
        given(userService.authenticate("tester@example.com","x"))
        .willThrow(WrongPasswordException.class);

        mvc.perform(post("/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"tester@example.com\",\"password\":\"x\"}"))
                .andExpect(status().isBadRequest());


        verify(userService).authenticate(eq("tester@example.com"),eq("x"));
    }
}