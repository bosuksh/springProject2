package kr.co.springExample2.eatgo.interfaces;

import lombok.Builder;
import lombok.Data;

@Data
public class SessionRequestDTO {

    private String email;

    private String password;

}
