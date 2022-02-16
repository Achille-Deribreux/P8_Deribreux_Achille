package tourGuide;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Locale;

@SpringBootApplication
@EnableSwagger2
public class Application {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        SpringApplication.run(Application.class, args);
    }

}
