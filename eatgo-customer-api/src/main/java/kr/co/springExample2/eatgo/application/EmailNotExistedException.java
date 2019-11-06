package kr.co.springExample2.eatgo.application;

public class EmailNotExistedException extends RuntimeException{
    EmailNotExistedException(String email) {
        super("Email is not registered " + email);
    }
}
