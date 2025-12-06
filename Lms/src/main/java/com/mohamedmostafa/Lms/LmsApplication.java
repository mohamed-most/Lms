package com.mohamedmostafa.Lms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(LmsApplication.class, args);

//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        String rawPassword = "admin123";
//        String encodedPassword = encoder.encode(rawPassword);
//        System.out.println(encodedPassword);
    }

}
