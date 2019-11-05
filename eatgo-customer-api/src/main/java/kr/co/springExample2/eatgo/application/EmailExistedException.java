package kr.co.springExample2.eatgo.application;

public class EmailExistedException extends RuntimeException{

    EmailExistedException(String email) {
        super("Email is already existed");
    }
}
