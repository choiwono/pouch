package my.examples.pouch.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")

@Import(MailConfig.class)
public class ApplicationConfig {
}
