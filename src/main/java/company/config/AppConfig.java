package company.config;

import company.utils.AppConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "company" })
public class AppConfig {
    @Bean
    AppConnection appConnection(){
        return new AppConnection();
    }
}