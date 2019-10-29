package kr.co.springExample2.eatgo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

//@PropertySource(value = {"application.yml"})
@SpringBootApplication
public class EatgoAdminApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EatgoAdminApiApplication.class, args);
	}

}
