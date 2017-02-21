package spring.hotel.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
public class Application {
    @Autowired
    private OAuth2ClientContext oauth2ClientContext;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public OAuth2ClientContext oAuth2ClientContext(){
        return oauth2ClientContext;
    }
}
