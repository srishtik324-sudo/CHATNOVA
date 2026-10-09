
package com.chatnova;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class ChatnovaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatnovaApplication.class, args);
    }
}