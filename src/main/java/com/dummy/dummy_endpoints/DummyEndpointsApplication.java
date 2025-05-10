package com.dummy.dummy_endpoints;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DummyEndpointsApplication {

    public static void main(String[] args) {
        SpringApplication.run(DummyEndpointsApplication.class, args);
    }

}
