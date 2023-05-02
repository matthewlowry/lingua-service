package com.mclowry.linguaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication(
    scanBasePackageClasses = {
        com.mclowry.linguaserver.LinguaServerApplication.class
    }
)
@EnableConfigurationProperties
public class LinguaServerApplication {

    /**
     * Application entry point
     *
     * @param args Command-line arguements.
     */
    public static void main(String[] args) {
        SpringApplication.run(LinguaServerApplication.class, args);
    }

}
