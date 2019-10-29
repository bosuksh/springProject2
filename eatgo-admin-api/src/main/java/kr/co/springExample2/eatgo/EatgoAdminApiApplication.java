package kr.co.springExample2.eatgo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application.properties")
public class EatgoAdminApiApplication {

	public static void main(String[] args) {

		SpringApplication.run(EatgoAdminApiApplication.class, args);
	}

}
