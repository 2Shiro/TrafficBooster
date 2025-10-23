package com.andgivemarketing.trafficboosterweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.andgivemarketing")
@SpringBootApplication(scanBasePackages = {"com.andgivemarketing"})
public class TrafficBoosterWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrafficBoosterWebApplication.class, args);
    }

}
