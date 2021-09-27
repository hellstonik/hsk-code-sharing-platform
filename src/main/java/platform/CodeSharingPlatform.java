package platform;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import platform.persistance.CodeRepository;

@SpringBootApplication
public class CodeSharingPlatform {

    public static void main(String[] args) {
        SpringApplication.run(CodeSharingPlatform.class, args);
    }

    @Bean
    public CommandLineRunner runApplication(CodeRepository codeRepository) {
        return (args -> {
            // call methods you want to use

        });
    }

}