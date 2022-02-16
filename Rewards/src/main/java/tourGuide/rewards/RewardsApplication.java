package tourGuide.rewards;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Locale;

@SpringBootApplication
@EnableSwagger2
public class RewardsApplication {
    public static void main(String[] args) {
        // Set default locale due to DoubleParse error in GpsUtil (Provided for the Project)
        Locale.setDefault(Locale.US);

        SpringApplication.run(RewardsApplication.class, args);
    }
}
