package nbe1.team08.gridscircles;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GridsCirclesApplication {

	public static void main(String[] args) {
		SpringApplication.run(GridsCirclesApplication.class, args);
	}
}
