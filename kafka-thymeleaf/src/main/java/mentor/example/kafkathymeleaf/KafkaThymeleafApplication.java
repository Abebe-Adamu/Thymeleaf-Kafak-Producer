package mentor.example.kafkathymeleaf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("controller")
@ComponentScan("model")
@ComponentScan("config")
@SpringBootApplication
public class KafkaThymeleafApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaThymeleafApplication.class, args);
	}

}
