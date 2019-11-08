package kr.co.springExample2.eatgo.application;

public class WrongPasswordException extends RuntimeException{

    WrongPasswordException() {
        super("Password is Wrong");
    }
}
