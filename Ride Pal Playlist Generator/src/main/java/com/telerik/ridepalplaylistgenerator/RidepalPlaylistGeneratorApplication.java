package com.telerik.ridepalplaylistgenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class RidepalPlaylistGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(RidepalPlaylistGeneratorApplication.class, args);
        //test swagger -> http://localhost:8080/swagger-ui.html

    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
